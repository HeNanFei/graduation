package com.zjt.graduation.common.utils;

/**
 * @Author hyh
 * @Date: 2020/12/7 21:47
 * @Version 1.0
 */

import org.springframework.stereotype.Component;


import org.springframework.context.ApplicationContext;
@Component
public class ApplicationContextUtils {

    private static ApplicationContext applicationContext;

    public ApplicationContextUtils(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static String getConfig(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }
}
