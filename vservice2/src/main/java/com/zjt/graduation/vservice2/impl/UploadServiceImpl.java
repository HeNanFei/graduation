package com.zjt.graduation.vservice2.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.zjt.graduation.vservice2.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:16
 * @Version 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    private final String ACCESSKEYID = "LTAI4GJsSpSzw8di3fH6zziK";

    private final String ACCESSKEYSECRET = "W3IoXiwM19KJNtVFcmdenQUjyNeX0q";


    @Autowired
    private UploadVideoImpl uploader;

    @Override
    public UploadStreamResponse uploadVideo(String title,String fileName, InputStream inputStream) {
        UploadStreamRequest uploadStreamRequest = new UploadStreamRequest(ACCESSKEYID,ACCESSKEYSECRET,title,fileName,inputStream);
        UploadStreamResponse uploadStreamResponse = uploader.uploadStream(uploadStreamRequest);
        return uploadStreamResponse;
    }
}
