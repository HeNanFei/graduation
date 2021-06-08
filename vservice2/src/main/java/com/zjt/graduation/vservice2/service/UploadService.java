package com.zjt.graduation.vservice2.service;

import com.aliyun.vod.upload.resp.UploadStreamResponse;

import java.io.InputStream;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:15
 * @Version 1.0
 */
public interface UploadService {

    public UploadStreamResponse uploadVideo(String title,String fileName, InputStream inputStream);

}
