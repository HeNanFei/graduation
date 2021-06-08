package com.zjt.graduation.common.config.batisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(1)
@Configuration
public class PlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public MysqlInjector mysqlInjector(){
        return new MysqlInjector();
    }

    @Bean
    public GlobalConfig.DbConfig globalConfig() throws IllegalAccessException, InstantiationException {
        GlobalConfig.DbConfig dbConfig = GlobalConfig.DbConfig.class.newInstance();
        dbConfig.setIdType(IdType.AUTO);
        dbConfig.setLogicDeleteField("deleted");
        dbConfig.setTableUnderline(false);
        dbConfig.setLogicDeleteValue("REPLACE(unix_timestamp(current_timestamp(3)),'.','')");
        return dbConfig;
    }
}
