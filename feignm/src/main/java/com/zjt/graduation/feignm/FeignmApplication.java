package com.zjt.graduation.feignm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.zjt.graduation","com.zjt.graduation.common.config.exception "})
public class FeignmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignmApplication.class, args);
    }

}
