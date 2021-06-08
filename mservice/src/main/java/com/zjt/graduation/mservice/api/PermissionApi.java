package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.entity.Permission;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "权限相关")
@RequestMapping("/mservice/permission")
public interface PermissionApi {
    @ApiOperation(value = "获取所有权限")
    @PostMapping("/getAllPermission")
    CommonResult getAll(@RequestBody(required = false) ComplexQueryRequest<PermissionRequestDTO> requestDTO);

    @ApiOperation(value = "根据id删除权限")
    @GetMapping("/deletePermissionById")
    CommonResult deletePermissionByid(@RequestParam(required = true) Long id);

    @ApiOperation(value = "修改权限")
    @PostMapping("/updatePermission")
    CommonResult updatePermission(@RequestBody(required = false) Permission permission);

    @ApiOperation(value = "添加权限")
    @PostMapping("/addPermission")
    CommonResult addPermission(@RequestBody(required = false) Permission permission);
}
