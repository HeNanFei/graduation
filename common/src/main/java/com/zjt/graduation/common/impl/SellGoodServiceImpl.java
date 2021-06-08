package com.zjt.graduation.common.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.dto.order.Messager;
import com.zjt.graduation.common.entity.Orders;
import com.zjt.graduation.common.entity.SellGood;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.mapper.GoodsMapper;
import com.zjt.graduation.common.mapper.OrderMapper;
import com.zjt.graduation.common.mapper.SysUserInfoMapper;
import com.zjt.graduation.common.service.SellGoodService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class SellGoodServiceImpl extends ServiceImpl<GoodsMapper, SellGood> implements SellGoodService {

    private static  final String ORDER_PREFIX= "ORDER_PREFIX_";

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Integer insertBatch(List<SellGood> list) {
        return goodsMapper.insertBatchSomeColumn(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String  purchase(Long id,Integer number,Long userId) {
        SellGood sellGood = goodsMapper.selectById(id);
        if(sellGood.getNumber()> 0 ){
            if(redisTemplate.opsForValue().setIfAbsent(ORDER_PREFIX+sellGood.getGoodName(),id,2L, TimeUnit.SECONDS)){
                //生成订单
                SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(userId);
                BigDecimal cost = new BigDecimal(number);
                cost = cost.multiply(BigDecimal.valueOf(number));
                Orders orders = new Orders(null,id,sellGood.getGoodName(),1,"待支付", LocalDateTime.now(), Optional.ofNullable(sysUserInfo.getUsername()).orElse(null),cost,null,Optional.ofNullable(sysUserInfo.getSex()).orElse(null),number);
                orderMapper.insert(orders);

                //生成临时记录
                redisTemplate.opsForList().leftPush(ORDER_PREFIX+sellGood.getGoodName()+"_record",userId);

                //减库
                Long aLong = goodsMapper.cutDownGoodsNumber(number, id);

                if(aLong>0){
                    Messager messager = new Messager(userId, orders.getId());
                    CorrelationData correlationData = new CorrelationData();
                    String s = JSONUtil.toJsonStr(messager);
                    rabbitTemplate.convertAndSend("delayExchange","delayRoute", s,correlationData);
                }
                return "抢购成功";
            }else{
                return "没抢到，下次再试试吧~";
            }
        }
        return "商品已售罄";
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(2.5));
        System.out.println(multiply);
    }
}
