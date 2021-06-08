package com.zjt.graduation.common.config.redis;

import org.springframework.context.annotation.Configuration;

/**
 * @Author hyh
 * @Date: 2020/10/4 20:54
 * @Version 1.0
 */
@Configuration
public class RedisConfig {
   /* @Bean
    public StringRedisTemplate redisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }*/
}
