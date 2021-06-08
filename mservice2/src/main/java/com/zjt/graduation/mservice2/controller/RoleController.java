package com.zjt.graduation.mservice2.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjt.graduation.common.entity.Role;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.RoleService;
import com.zjt.graduation.mservice2.api.RoleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class RoleController implements RoleApi {

    @Autowired
    private RoleService roleService;


    @Override
    public CommonResult queryRole(ComplexQueryRequest requestDTO) {
        return CommonResult.success(roleService.getAllRoleBypage(requestDTO));
    }



    @Override
    public CommonResult addRole(Role role) {
        return CommonResult.success(roleService.save(role));
    }

    @Override
    public CommonResult updateRole(Role role) {
        return CommonResult.success(roleService.update(role,new UpdateWrapper<Role>().eq("id",role.getId())));
    }

    @Override
    public CommonResult deleteRole(Long id) {
        return CommonResult.success(roleService.removeById(id));
    }
}

