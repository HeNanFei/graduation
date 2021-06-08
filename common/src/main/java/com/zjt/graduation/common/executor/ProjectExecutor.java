package com.zjt.graduation.common.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ProjectExecutor {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(13,46,8L, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(200));
        return threadPoolExecutor;
    }
}
