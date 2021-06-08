package com.zjt.graduation.common.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/15 11:18
 */
@Component
public class ReturnCallB implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(replyText);
    }
}
