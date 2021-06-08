/*
package com.zjt.graduation.serverfile.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

*/
/**
 * @Author hyh
 * @Date: 2020/10/26 11:48
 * @Version 1.0
 *//*

@Component
@Lazy(false)
public class ApplicationContextUtils implements ApplicationContextAware{

    private static ApplicationContext applicationContext;
    private  ApplicationContext applicationContext2;


    public ApplicationContextUtils(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {

        return applicationContext.getBean(clazz);
    }
    public  <T> T getBean2(Class<T> clazz) {
        return applicationContext2.getBean(clazz);

    }
    public static String getConfig(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext2 = applicationContext;
    }
}
*/
