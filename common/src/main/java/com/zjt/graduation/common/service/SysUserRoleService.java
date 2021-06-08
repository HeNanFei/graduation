package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.sysUserRole.SysUserRoleResponseDTO;
import com.zjt.graduation.common.entity.Role;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    List<SysUserRoleResponseDTO> querySysUserRoleByUserId(Long userId);

    Boolean updateUserInfo(SysUserInfo sysUserInfo);

}
