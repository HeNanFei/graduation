package com.zjt.graduation.mservice.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.entity.Permission;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.PermissionService;
import com.zjt.graduation.mservice.api.PermissionApi;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class PermissionController implements PermissionApi {
    @Autowired
    private PermissionService permissionService;

    @Override
    public CommonResult getAll(ComplexQueryRequest<PermissionRequestDTO> requestDTO) {
        return CommonResult.success(permissionService.getAllPermissionPage(requestDTO));
    }

    @Override
    public CommonResult deletePermissionByid(Long id) {
        if(permissionService.removeById(id)){
            return CommonResult.success(permissionService.getById(id));
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult updatePermission(Permission permission) {
        if(permissionService.update(permission,new UpdateWrapper<Permission>().eq("id",permission.getId()))){
            return CommonResult.success(permission);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult addPermission(Permission permission) {
        if( permissionService.save(permission)){
            return CommonResult.success(permission);
        }
        return CommonResult.failed();
    }
}

