package com.zjt.graduation.serverfile.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.notice.NoticeMessageDTO;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.search.SearchDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 13:56
 */
@Api(tags = "Noticemessage Api")
@RequestMapping("/notice/")
public interface NoticeMessageApi {
    @ApiOperation(value = "pageNoticeMessage")
    @PostMapping("/pageNoticeMessage")
    CommonResult pageNoticeMessage(@RequestBody Page page) ;

    @ApiOperation(value = "detailForNoticeMessage")
    @PostMapping("/detailForNoticeMessage")
    CommonResult detailForNoticeMessage(@RequestParam Long id) ;


    @ApiOperation(value = "deleteNoticeMessage")
    @PostMapping("/deleteNoticeMessage")
    CommonResult deleteNoticeMessage(@RequestParam Long id) ;


    @ApiOperation(value = "createNoticeMessage")
    @PostMapping("/createNoticeMessage")
    CommonResult createNoticeMessage(@RequestBody NoticeMessageDTO noticeMessageDTO) ;


    @ApiOperation(value = "getBelongNoticeMessage")
    @PostMapping("/getBelongNoticeMessage")
    CommonResult getBelongNoticeMessage(@RequestBody SearchDao searchDao) ;

    @ApiOperation(value = "getClassIdAndName")
    @PostMapping("/getClassIdAndName")
    CommonResult getClassIdAndName() ;









}
