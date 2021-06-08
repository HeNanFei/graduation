package com.zjt.graduation.mservice2.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hyh
 * @Date: 2020/10/30 21:49
 * @Version 1.0
 */
@Configuration
public class ClientConfig2 {

    @Bean
    public DefaultClientConfigImpl defaultClientConfig(){
        return new DefaultClientConfigImpl();
    }
}
