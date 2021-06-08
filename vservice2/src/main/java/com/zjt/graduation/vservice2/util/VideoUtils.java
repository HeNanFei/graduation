package com.zjt.graduation.vservice2.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.SearchMediaRequest;
import com.aliyuncs.vod.model.v20170321.SearchMediaResponse;
import com.zjt.graduation.vservice2.config.PropertiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/14 11:39
 */
@Component
public class VideoUtils {
    @Autowired
    private PropertiConfig propertiConfig;


    /**
     * 搜索媒资信息
     * @param client 发送请求客户端
     * @return SearchMediaResponse 搜索媒资信息响应数据
     * @throws Exception

     */
    public  SearchMediaResponse searchMedia(DefaultAcsClient client,Integer page,Integer size) throws Exception {
        SearchMediaRequest request = new SearchMediaRequest();
        request.setFields("Title,CoverURL,Status");
        //request.setMatch("Status in ('Normal','Checking') and CreationTime = ('2018-07-01T08:00:00Z','2018-08-01T08:00:00Z')");
        request.setPageNo(page);
        request.setPageSize(size);
//        request.setSearchType("video");
        request.setSortBy("CreationTime:Desc");
        return client.getAcsResponse(request);
    }
}
