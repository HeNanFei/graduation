package com.zjt.graduation.vservice2.api;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.dto.source.SourceAddRequestDTO;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author hyh
 * @Date: 2020/10/4 15:05
 * @Version 1.0
 */
@Api(tags = "source相关Api")
@RequestMapping("/common/source")
public interface SourceApi {

    @ApiOperation(value = "添加source")
    @PostMapping(value = "/addSource")
    CommonResult addSource(@RequestBody SourceAddRequestDTO sourceAddRequestDTO) throws IOException;


    @ApiOperation(value = "添加Video")
    @PostMapping(value = "/addVideo")
    CommonResult addVideo(String sourceName,@RequestParam(value = "file",defaultValue = "file") MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) throws IOException;

    @AvoidSubmit(requestTimes = 2)
    @ApiOperation(value = "返回url")
    @GetMapping(value = "/getUrlBack")
    CommonResult addSource(@RequestParam String videoId) throws IOException, ClientException;

    @ApiOperation(value = "返回视频列表")
    @PostMapping(value = "/getVideoBack")
    CommonResult getVideoBack(@RequestBody Page page) throws Exception;



}
