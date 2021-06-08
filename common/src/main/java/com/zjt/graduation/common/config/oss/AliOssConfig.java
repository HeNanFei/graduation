package com.zjt.graduation.common.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ali.oss")
@Component
@Data
public class AliOssConfig {
    private String bucket;

    private String secret;

    private String endpoint;

    private String bucketname;
}
