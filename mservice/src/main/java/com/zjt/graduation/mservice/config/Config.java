/*
package com.zjt.graduation.mservice.config;

import com.zjt.graduation.common.config.clazz.ConfigProperties;
import com.zjt.graduation.common.config.swagger.Swagger2Config;
import com.zjt.graduation.common.security.components.DynamicAccessDecisionManager;
import com.zjt.graduation.common.security.components.DynamicSecurityFilter;
import com.zjt.graduation.common.security.components.DynamicSecurityMetadataSource;
import com.zjt.graduation.common.security.components.;
import com.zjt.graduation.common.security.config.MyUserDetailService;
import com.zjt.graduation.common.security.config.SecuModuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.Map;

@Configuration
public class Config extends SecuModuleConfig {

    @Bean
    ConfigProperties getConfigProperties(){
        return new ConfigProperties();
    }
    @Bean
    MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }
    @Bean
    DynamicSecurityFilter dynamicSecurityFilte(){
        return new DynamicSecurityFilter();
    }
    @Bean(name = {"dynamicSecurityMetadataSource"})
    DynamicSecurityMetadataSource dynamicSecurityMetadataSourc(){
        return new DynamicSecurityMetadataSource();
    }
    @Bean
    DynamicSecurityService dynamicSecurityServic(){
        return  new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                return null;
            }
        };
    }
    @Bean
    DynamicAccessDecisionManager dynamicAccessDecisionManage(){
        return new DynamicAccessDecisionManager();
    }
    @Bean(name = {"dynamicSecurityFilter"})
    DynamicSecurityFilter dynamicSecurityFile(){
        return  new DynamicSecurityFilter();
    }
    @Bean
    Swagger2Config swagger2Config(){
        return  new Swagger2Config();
    }
    @Bean
    Swagger2DocumentationConfiguration swagger2DocumentationConfiguration(){
        return new Swagger2DocumentationConfiguration();
    }
}
*/
