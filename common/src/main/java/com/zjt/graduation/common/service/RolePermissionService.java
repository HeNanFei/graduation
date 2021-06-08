package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionRequestDTO;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.query.ComplexQueryRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<RolePermissionResponseDTO> getRolePermissionByRoleId(@NotNull Long roleId);

    Page<RolePermissionResponseDTO> getRolePermissionPage(ComplexQueryRequest<RolePermissionRequestDTO> requestDTO);



}
