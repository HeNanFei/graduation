package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.entity.Menu;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "菜单相关的API")
@RequestMapping("/mservice/menu")
public interface MenuApi {
    @ApiOperation(value = "获取所有菜单")
    @PostMapping("/getAllMenu")
    CommonResult getAll(ComplexQueryRequest<Menu> requestDTO);

    @ApiOperation(value = "根据id删除菜单")
    @GetMapping("/deleteMenuByUserId")
    CommonResult deleteMenuByid(@RequestParam(required = true) Long id);

    @ApiOperation(value = "修改菜单")
    @PostMapping("/updateMenu")
    CommonResult updateMenu(@RequestBody(required = false) Menu menu);

    @ApiOperation(value = "添加菜单")
    @PostMapping("/addMenu")
    CommonResult addMenu(@RequestBody(required = false) Menu menu);

}
