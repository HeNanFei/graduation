package com.zjt.graduation.vservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zjt")
public class Vservice2Application {

    public static void main(String[] args) {
        SpringApplication.run(Vservice2Application.class, args);
    }

}
