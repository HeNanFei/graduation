package com.zjt.graduation.mservice.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionRequestDTO;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.entity.Permission;
import com.zjt.graduation.common.entity.RolePermission;
import com.zjt.graduation.common.enums.RolePermissionEnum;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.PermissionService;
import com.zjt.graduation.common.service.RolePermissionService;
import com.zjt.graduation.mservice.api.RolePermissionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class RolePermissionController implements RolePermissionApi{

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public CommonResult queryRolePermission(ComplexQueryRequest<RolePermissionRequestDTO> requestDTO) {
        SecurityContextHolder.getContext().getAuthentication();
        if(CollectionUtil.isEmpty(rolePermissionService.getRolePermissionPage(requestDTO).getRecords())){
            return CommonResult.success(new Page<RolePermissionResponseDTO>());
        }
        return CommonResult.success(rolePermissionService.getRolePermissionPage(requestDTO));
    }

    @Override
    public CommonResult addRolePermission(RolePermission rolePermission) {
        if(rolePermissionService.save(rolePermission)){
            return CommonResult.success(rolePermission);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult<RolePermission> updateRolePermission(RolePermission role) {
        if(rolePermissionService.update(role,new UpdateWrapper<RolePermission>().eq("id",role.getId()))){
            return CommonResult.success(role);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult deleteRolePermission(Long id) {
        if(rolePermissionService.removeById(id)){
            return CommonResult.success(RolePermissionEnum.DELETE_SUCCESS);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult getRolePermissionById(Long id) {
        return null;
    }

    @Override
    public CommonResult authorization(Long roleId, Integer typeId) {
        List<Permission> permissionList = permissionService.list(new QueryWrapper<Permission>().eq("type", typeId));
        List<Long> permissionId = permissionList.stream().map(n -> n.getId()).collect(Collectors.toList());
        for (int i = 0; i < permissionId.size(); i++) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId.get(i));
            rolePermission.setRoleId(roleId);
            rolePermissionService.save(rolePermission);
        }
        return null;
    }
}

