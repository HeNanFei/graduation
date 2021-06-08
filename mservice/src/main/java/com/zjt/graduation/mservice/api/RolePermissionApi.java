package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.dto.rolePermission.RolePermissionRequestDTO;
import com.zjt.graduation.common.entity.RolePermission;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色权限APi",value = "角色权限相关")
@RequestMapping("/mservice/role-permission")
public interface RolePermissionApi {
    @ApiOperation(value = "条件获取角色权限")
    @PostMapping("/queryRolePermission")
    CommonResult queryRolePermission(@RequestBody(required = false) ComplexQueryRequest<RolePermissionRequestDTO> requestDTO);

    @ApiOperation(value = "添加角色权限")
    @PostMapping("/addRolePermission")
    CommonResult addRolePermission(@RequestBody(required = false) RolePermission rolePermission);

    @ApiOperation(value = "修改角色权限")
    @PostMapping("/updateRolePermission")
    CommonResult<RolePermission> updateRolePermission(@RequestBody(required = false) RolePermission role);

    @ApiOperation(value = "删除角色权限")
    @GetMapping("/deleteRolePermission")
    CommonResult deleteRolePermission(@RequestParam Long id);


    @ApiOperation(value = "根据角色id获取角色权限")
    @GetMapping("/getRolePermissionById")
    CommonResult getRolePermissionById(@RequestParam Long id);


    @ApiOperation(value = "授权接口")
    @GetMapping("/authorization")
    CommonResult authorization(@RequestParam Long roleId,@RequestParam Integer typeId);





}
