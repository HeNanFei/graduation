package com.zjt.graduation.vservice2.vconfig;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Author hyh
 * @Date: 2020/10/4 15:13
 * @Version 1.0
 */
@Configuration
public class UploadConfig {

    @Bean("uploadVideoImpl")
    public UploadVideoImpl uploadVideo(){
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        return uploadVideo;
    }



    @Bean(name="multipartResolver")
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    }
