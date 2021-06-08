package com.zjt.graduation.serverfile.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @Date: 2021/3/21 19:23
 */
@Api(tags = "student Api")
@RequestMapping("/student/")
public interface SystemStudentApi {
    @ApiOperation(value = "pageStudentData")
    @PostMapping("/pageStudentData")
    CommonResult pageStudentData(@RequestBody Page page) ;

    @ApiOperation(value = "detailForStudent")
    @PostMapping("/detailForStudent")
    CommonResult detailForStudent(@RequestParam Long id) ;


    @ApiOperation(value = "deleteStudent")
    @PostMapping("/deleteStudent")
    CommonResult deleteStudent(@RequestParam Long id) ;


    @ApiOperation(value = "searchResultPage")
    @PostMapping("/searchResultPage")
    CommonResult searchResultPage(@RequestBody SearchDao searchDao) ;



}
