package com.zjt.graduation.mservice2.api;

import com.zjt.graduation.common.entity.SysUserRole;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户角色API")
@RequestMapping("/mservice/sys-user-role")
public interface SysUserRoleApi {

    @ApiOperation(value = "添加用户角色")
    @PostMapping("/addSysUserRole")
    CommonResult addSysUser(@RequestBody(required = false) SysUserRole sysUserRole);

    @ApiOperation(value = "根据id查询用户角色")
    @PostMapping("/queryAllSysUserRoleById")
    CommonResult queryAllUserRoleByUserId(@RequestParam(required = true) Long idO);

    @ApiOperation(value = "根据id删除用户角色")
    @GetMapping("/deleteUserRoleById")
    CommonResult deleteSysUserRoleByRoleId(List<Long> id);

    @ApiOperation(value = "修改用户角色")
    @PostMapping("/updateUserInfor")
    CommonResult<Boolean> updateUserInfor(@RequestBody(required = false) SysUserRole sysUserRole);


}
