package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zjt.graduation.common.entity.TGood;
import com.zjt.graduation.common.service.TGoodService;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import com.zjt.graduation.serverfile.event.ImportEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class TGoodListener extends AnalysisEventListener<TGood> {
    ThreadLocal<LocalDateTime> threadLocal = new ThreadLocal<>();


    private List<TGood> tGoodList;

    private TGoodService tGoodService;

    private ThreadPoolExecutor taskExecutor;

    private Map<String,List<String>> checkMap;

    private ApplicationContext applicationContext;

    private String redisRecodKey;

    public TGoodListener(TGoodService tGoodService, ThreadPoolExecutor taskExecutor, Map<String,List<String>> checkMaps,ApplicationContext applicationContext,String redisRecodKeys){
        this.taskExecutor = taskExecutor;
        tGoodList = new ArrayList<>();
        this.tGoodService = tGoodService;
        threadLocal.set(LocalDateTime.now());
        this.checkMap = checkMaps;
        this.applicationContext = applicationContext;
        this.redisRecodKey = redisRecodKeys;
    }

    @Override
    public void invoke(TGood tGood, AnalysisContext analysisContext) {
        List<String> platform_goods_code = checkMap.get("platform_goods_code");
        List<String> goods_code = checkMap.get("goods_code");
        List<String> brand_code = checkMap.get("brand_code");
        List<String> category_code = checkMap.get("category_code");
        List<String> category_name = checkMap.get("category_name");
        List<String> name = checkMap.get("name");
        List<String> second_category_code = checkMap.get("second_category_code");
        List<String> second_category_name = checkMap.get("second_category_name");
        List<String> three_category_code = checkMap.get("three_category_code");
        List<String> three_category_name = checkMap.get("three_category_name");
        List<String> brand_name = checkMap.get("brand_name");
        List<String> title = checkMap.get("title");
        Boolean platform_goods_code2 = platform_goods_code.contains(tGood.getPlatform_goods_code());
        Boolean goods_code2 = goods_code.contains(tGood.getGoods_code());
        Boolean brand_code2 = brand_code.contains(tGood.getBrand_code());
        Boolean category_code2 = category_code.contains(tGood.getCategory_code());
        Boolean category_name2 = category_name.contains(tGood.getCategory_name());
        Boolean name2 = name.contains(tGood.getName());
        Boolean second_category_code2 =second_category_code.contains(tGood.getSecond_category_code());
        Boolean second_category_name2 =second_category_name.contains(tGood.getSecond_category_name());
        Boolean three_category_code2 = three_category_code.contains(tGood.getThree_category_code());
        Boolean three_category_name2 = three_category_name.contains(tGood.getThree_category_name());
        Boolean brand_name2 = brand_name.contains(tGood.getBrand_name());
        Boolean title2 = title.contains(tGood.getTitle());
        if(platform_goods_code2 && goods_code2 && brand_code2 && category_code2 && category_name2 && name2 && second_category_code2 && second_category_name2 && three_category_code2 && three_category_name2 && brand_name2 && title2){
            tGoodList.add(tGood);
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        RedisTemplate redisTemplate =(RedisTemplate) ApplicationContextUtils.getContext().getBean("redisTemplate");
        //RedisTemplate redisTemplate = ApplicationContextUtils.getBean(RedisTemplate.class);
        //LocalDateTime localDateTime = threadLocal.get();
        LocalDateTime now = LocalDateTime.now();
        Map<String,String> importRecord =(Map<String,String>) redisTemplate.opsForHash().get("importRecord", redisRecodKey);
        importRecord.put("status","analysis finished--- start import");
        Duration between = Duration.between(LocalDateTime.parse(importRecord.get("importTime")), now);
        importRecord.put("costTime",between.getSeconds()+"s");
        importRecord.put("message","data is importing");
        redisTemplate.opsForHash().put("importRecord",redisRecodKey,importRecord);
        applicationContext.publishEvent(new ImportEvent(applicationContext,tGoodList,TGoodService.class,3000,redisRecodKey));
    }
}
