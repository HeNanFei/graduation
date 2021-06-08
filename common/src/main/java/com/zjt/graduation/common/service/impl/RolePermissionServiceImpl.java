package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionRequestDTO;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.entity.RolePermission;
import com.zjt.graduation.common.mapper.RolePermissionMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<RolePermissionResponseDTO> getRolePermissionByRoleId(@NotNull Long roleId) {
        return baseMapper.getRolePermissionByRoleid(roleId);
    }

    @Override
    public Page<RolePermissionResponseDTO> getRolePermissionPage(ComplexQueryRequest<RolePermissionRequestDTO> requestDTO) {
        return baseMapper.getRolePermissionPage(requestDTO.getPage(),requestDTO.queryWrapper());
    }
}
