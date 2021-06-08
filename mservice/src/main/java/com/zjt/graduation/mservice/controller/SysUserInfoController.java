package com.zjt.graduation.mservice.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoServiceRequestDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.response.ResultCode;
import com.zjt.graduation.common.service.SysUserInfoService;
import com.zjt.graduation.mservice.api.SysUserInfoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class SysUserInfoController implements SysUserInfoApi {
    @Autowired
    private SysUserInfoService sysUserInfoService;
    @Override
    public CommonResult<SysUserInfo> addSysUser(SysUserInfo sysUserInfo) {
        sysUserInfoService.save(sysUserInfo);
        return CommonResult.success(sysUserInfo);
    }

    @Override
    public CommonResult queryAllUser(ComplexQueryRequest<SysUserInfoServiceRequestDTO> requestDTO) {
        System.out.println("this is one");
        return CommonResult.success(sysUserInfoService.getAllUserByList(requestDTO));
    }

    @Override
    public CommonResult queryUserPage(Page page) {
        return CommonResult.success(sysUserInfoService.page(page));
    }

    @Override
    public CommonResult<SysUserInfo> getUserInfoById(Long id) {
        SysUserInfo byId = sysUserInfoService.getById(id);
        if (byId.equals(null)){
            return CommonResult.failed();
        }
        return CommonResult.success(byId);
    }

    @Override
    public CommonResult<Boolean> deleteUserInfoById(Long id) {
        return CommonResult.success(sysUserInfoService.removeById(id)); }

    @Override
    public CommonResult<Boolean> updateUserInfor(SysUserInfo sysUserInfo) {
        //sysUserInfoService.list(new QueryWrapper<SysUserInfo>().eq("any","any").or());
        return CommonResult.success(sysUserInfoService.update(sysUserInfo,new UpdateWrapper<SysUserInfo>().eq("id",sysUserInfo.getId())));
    }

    @Override
    public CommonResult login(SysUserInfo sysUserInfo) {
        UserDetails userDetails = sysUserInfoService.userLogin(sysUserInfo);
        return CommonResult.success(userDetails);
    }

    @Override
    public CommonResult checkVerifiedCode(String verifiedCode) {
        if(sysUserInfoService.checkVerifiedCode(verifiedCode)){
            return CommonResult.success(verifiedCode);
        }
        return CommonResult.failed(ResultCode.INVALID_VERIFIED_CODE);
    }
}

