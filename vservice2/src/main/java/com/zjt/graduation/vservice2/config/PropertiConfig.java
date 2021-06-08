package com.zjt.graduation.vservice2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/14 11:36
 */
@Component
@ConfigurationProperties(prefix = "ali.video")
public class PropertiConfig {
    private String accessKeyId;

    private String accessKeySecret;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Override
    public String toString() {
        return "PropertiConfig{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                '}';
    }
}
