package com.zjt.graduation.mservice.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoServiceRequestDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用戶信息及账号相关APi")
@RequestMapping("/mservice/sys-user-info")
public interface SysUserInfoApi {

    @ApiOperation(value = "添加用户")
    @PostMapping("/addSysUser")
    CommonResult<SysUserInfo> addSysUser(@RequestBody(required = false) SysUserInfo sysUserInfo);

    @ApiOperation(value = "查询用户信息")
    @PostMapping("/queryAllUser")
    CommonResult queryAllUser(@RequestBody(required = false) ComplexQueryRequest<SysUserInfoServiceRequestDTO> requestDTO);


    @ApiOperation(value = "查询用户信息2")
    @PostMapping("/queryUserPage")
    CommonResult queryUserPage(@RequestBody Page page);

    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/getUserInfoById")
    CommonResult<SysUserInfo> getUserInfoById(@RequestParam(required = true) Long id);

    @ApiOperation(value = "根据id删除用户信息")
    @GetMapping("/deleteUserInfoById")
    CommonResult<Boolean> deleteUserInfoById(@RequestParam(required = true) Long id);

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUserInfor")
    CommonResult<Boolean> updateUserInfor(@RequestBody(required = false) SysUserInfo sysUserInfo);

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    CommonResult login(@RequestBody(required = false) SysUserInfo sysUserInfo);

    @ApiOperation(value = "验证码校验接口")
    @PostMapping("/checkVerifiedCode")
    CommonResult checkVerifiedCode(String verifiedCode);


}
