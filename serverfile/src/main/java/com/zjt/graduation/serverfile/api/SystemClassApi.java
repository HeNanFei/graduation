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
@Api(tags = "class Api")
@RequestMapping("/class/")
public interface SystemClassApi {

    @ApiOperation(value = "pageClassData")
    @PostMapping("/pageClassData")
    CommonResult pageClassData(@RequestBody Page page) ;

    @ApiOperation(value = "detailForClass")
    @PostMapping("/detailForClass")
    CommonResult detailForClass(@RequestParam Long id) ;


    @ApiOperation(value = "deleteClass")
    @PostMapping("/deleteClass")
    CommonResult deleteClass(@RequestParam Long id) ;


    @ApiOperation(value = "searchResultPage")
    @PostMapping("/searchResultPage")
    CommonResult searchResultPage(@RequestBody SearchDao searchDao) ;


}
