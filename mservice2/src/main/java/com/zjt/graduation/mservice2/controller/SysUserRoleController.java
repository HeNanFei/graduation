package com.zjt.graduation.mservice2.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.zjt.graduation.common.entity.SysUserRole;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.SysUserRoleService;
import com.zjt.graduation.mservice2.api.SysUserRoleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class SysUserRoleController implements SysUserRoleApi {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public CommonResult addSysUser(SysUserRole sysUserRole) {
        if(sysUserRoleService.save(sysUserRole)){
            return CommonResult.success(sysUserRole);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult queryAllUserRoleByUserId(Long id) {
        if(!CollectionUtil.isEmpty(sysUserRoleService.querySysUserRoleByUserId(id))){
            return CommonResult.success(sysUserRoleService.querySysUserRoleByUserId(id));
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult deleteSysUserRoleByRoleId(List<Long> id) {
        if(sysUserRoleService.removeByIds(id)){
            return CommonResult.success(id);
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult<Boolean> updateUserInfor(SysUserRole sysUserRole) {
        return null;
    }
}

