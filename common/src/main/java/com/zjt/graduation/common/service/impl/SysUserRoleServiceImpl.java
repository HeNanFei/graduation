package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.dto.sysUserRole.SysUserRoleResponseDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.entity.SysUserRole;
import com.zjt.graduation.common.mapper.SysUserRoleMapper;
import com.zjt.graduation.common.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<SysUserRoleResponseDTO> querySysUserRoleByUserId(Long id) {
        return baseMapper.getSysUserRoleByUserId(id);
    }

    @Override
    public Boolean updateUserInfo(SysUserInfo sysUserInfo) {

        return null;
    }
}
