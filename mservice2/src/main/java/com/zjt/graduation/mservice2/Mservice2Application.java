package com.zjt.graduation.mservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.zjt.graduation","com.zjt.graduation.common.config.exception "})
public class Mservice2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mservice2Application.class, args);
    }

}
