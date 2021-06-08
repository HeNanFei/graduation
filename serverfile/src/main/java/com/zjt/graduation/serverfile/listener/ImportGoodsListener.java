package com.zjt.graduation.serverfile.listener;
import com.zjt.graduation.common.entity.TGood;
import com.zjt.graduation.common.security.utils.CollectionUtilForCut;
import com.zjt.graduation.common.service.TGoodService;
import com.zjt.graduation.serverfile.event.ImportEvent;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ImportGoodsListener<T> implements ApplicationListener<ImportEvent> {

    @Autowired
    private ThreadPoolExecutor taskExecutor;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    @SneakyThrows
    @Override
    public void onApplicationEvent(ImportEvent importEvent) {
        try {
            Integer perSize = importEvent.getPerSize();
            ApplicationContext source =(ApplicationContext) importEvent.getSource();
            TGoodService tGoodService = source.getBean(TGoodService.class);
            List<T> importData = importEvent.getImportData();

            List<List<T>> cutLists = CollectionUtilForCut.cutList(perSize, importData);
            List<Callable<Integer>> callableList = new ArrayList<>();
            Callable<Integer> callable = null;
            CountDownLatch countDownLatch = new CountDownLatch(cutLists.size());

            //循环添加线程
            for (int i = 0; i < cutLists.size(); i++) {
                int finalI = i;
                callable = new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        List<TGood> subData =(List<TGood>) cutLists.get(finalI);
                        List<TGood> newList = new ArrayList<>();
                        newList.addAll(subData);
                        source.getBean(importEvent.getaClass());
                        try{
                            List<List<TGood>> lists = CollectionUtilForCut.cutList(1000, subData);
                            for (int j = 0; j < lists.size(); j++) {
                                tGoodService.insertBatch(lists.get(j));
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally {
                            countDownLatch.countDown();
                        }
                        return newList.size();
                    }
                };
                callableList.add(callable);
            }
            LocalDateTime startTime = LocalDateTime.now();
            //唤起线程
            List<Future<Integer>> futures = taskExecutor.invokeAll(callableList);
            countDownLatch.await();

            LocalDateTime endTime = LocalDateTime.now();

            //rabbit发送延时消息
            CorrelationData correlationData = new CorrelationData();
            redisTemplate.opsForValue().set("sendTime",LocalDateTime.now());
            rabbitTemplate.convertAndSend("delayExchange","delayRoute",importData,correlationData);

            Map<String,String> importRecord =(Map<String,String>) redisTemplate.opsForHash().get("importRecord", importEvent.getRedisRecordKey());
            importRecord.put("status","importfinish");
            importRecord.put("costTime",Duration.between(LocalDateTime.parse(importRecord.get("importTime")),endTime)+"s");
            importRecord.put("message","data is importing");
            importRecord.put("finishTime",LocalDateTime.now().toString());
            redisTemplate.opsForHash().put("importRecord",importEvent.getRedisRecordKey(),importRecord);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
