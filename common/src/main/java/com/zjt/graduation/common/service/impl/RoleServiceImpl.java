package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.role.RoleRequestDTO;
import com.zjt.graduation.common.dto.role.RoleResponseDTO;
import com.zjt.graduation.common.entity.Role;
import com.zjt.graduation.common.mapper.RoleMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Page<RoleResponseDTO> getAllRoleBypage(ComplexQueryRequest<RoleRequestDTO> requestDTOComplexQueryRequest) {
        return baseMapper.getRolepage(requestDTOComplexQueryRequest.getPage(),requestDTOComplexQueryRequest.queryWrapper());
    }
}
