package com.zjt.graduation.vservice2.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/14 11:42
 */
@Configuration
public class ConfigBean {

    @Autowired
    private PropertiConfig pro;

    @Bean(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public  DefaultAcsClient initVodClient() throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, pro.getAccessKeyId(), pro.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
