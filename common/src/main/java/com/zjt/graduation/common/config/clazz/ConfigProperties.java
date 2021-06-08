package com.zjt.graduation.common.config.clazz;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author hyh
 * @Date: 2020/12/7 22:59
 * @Version 1.0
 */
@Component
@Data
@Accessors(chain = true)
public class ConfigProperties {
    @Value("${ignore.url}")
    private String ignoreUrl;
}
