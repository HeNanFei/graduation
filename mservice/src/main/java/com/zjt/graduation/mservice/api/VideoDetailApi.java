package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.entity.VideoDetail;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "视频相关Api")
@RequestMapping("/mservice/video-detail")
public interface VideoDetailApi {

    @ApiOperation(value = "条件获取视频")
    @PostMapping("/queryVideoDetail")
    CommonResult queryVideoDetail(@RequestBody(required = false) ComplexQueryRequest requestDTO);

    @ApiOperation(value = "添加视频")
    @PostMapping("/addVideoDetail")
    CommonResult addVideoDetail(@RequestBody(required = false) VideoDetail videoDetail);

    @ApiOperation(value = "修改视频")
    @PostMapping("/updateVideoDetail")
    CommonResult updateVideoDetail(@RequestBody(required = false) VideoDetail videoDetail);

    @ApiOperation(value = "删除视频")
    @GetMapping("/deleteVideoDetail")
    CommonResult deleteVideoDetail(@RequestParam Long id);


}
