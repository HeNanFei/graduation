package com.zjt.graduation.feign2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.zjt")
public class Feign2Application {

    public static void main(String[] args) {
        SpringApplication.run(Feign2Application.class, args);
    }

}
