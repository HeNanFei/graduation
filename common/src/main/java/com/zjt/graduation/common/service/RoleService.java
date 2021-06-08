package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.role.RoleRequestDTO;
import com.zjt.graduation.common.dto.role.RoleResponseDTO;
import com.zjt.graduation.common.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface RoleService extends IService<Role> {
    public Page<RoleResponseDTO> getAllRoleBypage(ComplexQueryRequest<RoleRequestDTO> requestDTOComplexQueryRequest);
}
