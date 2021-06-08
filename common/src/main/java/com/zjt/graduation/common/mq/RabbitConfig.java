package com.zjt.graduation.common.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Autowired
    private ConfirmCallBack confirmCallBack;


    @Autowired
    private ReturnCallB returnCallB;

    /**
     * 使用DirectMessageListenerContainer，您需要确保ConnectionFactory配置了一个任务执行器，
     * 该执行器在使用该ConnectionFactory的所有侦听器容器中具有足够的线程来支持所需的并发性。
     * 默认连接池大小仅为5。
     *
     * 并发性基于配置的队列和consumersPerQueue。每个队列的每个使用者使用一个单独的通道，
     * 并发性由rabbit客户端库控制;默认情况下，它使用5个线程池;
     * 可以配置taskExecutor来提供所需的最大并发性。
     *
     * @param connectionFactory
     * @return
     */
    @Bean(name = "rabbitMessageListenerContainer")
    public DirectMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory){
        // 写的时候，默认使用DEFAULT_NUM_THREADS = Runtime.getRuntime().availableProcessors() * 2个线程
        DirectMessageListenerContainer container = new DirectMessageListenerContainer(connectionFactory);
        // 设置确认消息的模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setPrefetchCount(5);

        // 并发由setQueues和setConsumersPerQueue控制
        // 从哪些队列消费消息，就设置哪些队列
//        container.setQueues(orderQueue(), ttlQueue(), dlxQueue());
//        container.setQueueNames("", "", "", "");
        // 设置每个队列由几个消费者消费。
        // 如果容器已经在运行，该值可以动态设置。
        // 每个消费者使用一个Channel
        container.setConsumersPerQueue(5);
        //一次确认几个消息
        container.setMessagesPerAck(1);

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        //设置该属性，灵活设置并发 ,多线程运行。
        container.setTaskExecutor(taskExecutor);

        return container;
    }

    /**
     * 设置消息转换器，用于将对象转换成JSON数据
     * 可以通过converterAndSend将对象发送消息队列
     * 监听器也可以通过该工具将接受对象反序列化成java对象
     *
     * @return Jackson转换器
     */
   /* @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }*/

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setReturnCallback(returnCallB);
        rabbitTemplate.setConfirmCallback(confirmCallBack);
        return rabbitTemplate;
    }


    //延时队列与死信队列的绑定
    @Bean
    public Queue ttlQueue(){
        Map<String,Object> args = new HashMap<>();
        // 该队列的消息10s到期
        args.put("x-message-ttl", 60_000);
        // 设置死信队列交换器,（当队列消息TTL到期后依然没有消费，则加
        //入死信队列）
        args.put("x-dead-letter-exchange","deadExchanger");
        // 设置私信队列路由键,设置该队列所关联的死信交换器的routingKey，如果没有特殊指定，使用原
        //队列的routingKey
        args.put("x-dead-letter-routing-key","deadRoute");
        Queue queue = new Queue("delayQueue",true,false,false,args);
        return queue;
    }

    //延时交换机
    @Bean
    public Exchange delayExchange(){
        Map args = new HashMap();
        DirectExchange exchange = new DirectExchange("delayExchange", true, false, args);
        return exchange;
    }
    //延时交换机与延时队列的绑定
    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(ttlQueue())
                .to(delayExchange())
                .with("delayRoute")
                .noargs();
    }

    //死信交换机
    @Bean
    public Exchange dlxExchange(){
        Map<String, Object> args = new HashMap<>();
        DirectExchange exchange = new DirectExchange("deadExchanger", true, false, args);
        return exchange;
    }


    //死信队列和死信交换机绑定
    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with("deadRoute")
                .noargs();
    }

    //死信队列
    @Bean
    public Queue dlxQueue(){
        Map<String,Object> args = new HashMap<>();
        Queue dlq = new Queue("deadQueue",true,false,false, args);
        return dlq;
    }
}
