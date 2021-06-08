package com.zjt.graduation.vservice2.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.SearchMediaResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.source.SourceAddRequestDTO;
import com.zjt.graduation.common.mapper.SourceMapper;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.vservice2.api.SourceApi;
import com.zjt.graduation.vservice2.service.SourceService;
import com.zjt.graduation.vservice2.service.UploadService;
import com.zjt.graduation.vservice2.util.VideoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-10-04
 */
@RestController
public class SourceController implements SourceApi {


    @Autowired
    private SourceMapper sourceMapper;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private DefaultAcsClient defaultAcsClient;

    @Autowired
    private VideoUtils videoUtils;




    @Override
    public CommonResult  addSource(SourceAddRequestDTO sourceAddRequestDTO) throws IOException {
        Boolean uploadResult = false;

        uploadResult  = sourceService.addSource(sourceAddRequestDTO);

        return CommonResult.success(uploadResult);
    }

    @Override
    public CommonResult addVideo(String sourceName,MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean aBoolean = sourceService.addSingleSource(sourceName, multipartFile, request, response);
        return CommonResult.success(aBoolean);
    }

    @Override
    public CommonResult addSource(String videoId) throws IOException, ClientException {
        String urlBack = sourceService.getUrlBack(videoId);
        return CommonResult.success(urlBack);
    }

    @Override
    public CommonResult getVideoBack(@RequestBody Page page) throws Exception {
        SearchMediaResponse searchMediaResponse = videoUtils.searchMedia(defaultAcsClient,(int) page.getCurrent(),(int)page.getSize());
        return CommonResult.success(searchMediaResponse);
    }
}

