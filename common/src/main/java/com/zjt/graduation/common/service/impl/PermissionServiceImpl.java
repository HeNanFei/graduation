package com.zjt.graduation.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.dto.permission.PermissionResponseDTO;
import com.zjt.graduation.common.entity.Permission;
import com.zjt.graduation.common.mapper.PermissionMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.security.utils.BeanUtils;
import com.zjt.graduation.common.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionService permissionService;
    @Override
    public Page<PermissionResponseDTO> getAllPermissionPage(ComplexQueryRequest<PermissionRequestDTO> requestDTOComplexQueryRequest) {
        return baseMapper.getPermissionByPage(requestDTOComplexQueryRequest.getPage(), requestDTOComplexQueryRequest.queryWrapper());
    }

    @Override
    public List<PermissionResponseDTO> getAllPermissionWithoutPage() {
        List<Permission> list = permissionService.list();
        List<PermissionResponseDTO> permissionResponseDTOS = BeanUtils.batchTransform(PermissionResponseDTO.class, list);
        return permissionResponseDTOS;

    }

}
