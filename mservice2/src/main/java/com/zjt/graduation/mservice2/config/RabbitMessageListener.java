package com.zjt.graduation.mservice2.config;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.zjt.graduation.common.dto.order.Messager;
import com.zjt.graduation.common.entity.Orders;
import com.zjt.graduation.common.entity.SellGood;
import com.zjt.graduation.common.service.OrderService;
import com.zjt.graduation.common.service.SellGoodService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class RabbitMessageListener implements ChannelAwareMessageListener {

    private static  final String ORDER_PREFIX= "ORDER_PREFIX_";


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellGoodService sellGoodService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "deadQueue" , ackMode = "MANUAL")
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try{
            //System.out.println(String.valueOf(message.getBody()));
            String jsonString = new String(message.getBody(),"UTF-8");


            //订单超时未支付
            Messager messager = JSONUtil.toBean(jsonString, Messager.class);

            Orders byId = orderService.getById(messager.getOrderId());
            byId.setStatus(2);
            byId.setStatusText("订单超时关闭");
            orderService.updateById(byId);

            Long sellGoodId = byId.getSellGoodId();
            SellGood sellGood = sellGoodService.getById(sellGoodId);
            Integer total = sellGood.getNumber()+byId.getNumber();
            sellGood.setNumber(total);
            sellGoodService.updateById(sellGood);

            List<String> range = redisTemplate.opsForList().range(ORDER_PREFIX + sellGood.getGoodName() + "_record", 0, 10000);
            if(!CollectionUtils.isEmpty(range)){
                redisTemplate.opsForList().leftPop(ORDER_PREFIX + sellGood.getGoodName() + "_record");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }catch (Exception e){
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,false);
            e.printStackTrace();
        }

    }
}
