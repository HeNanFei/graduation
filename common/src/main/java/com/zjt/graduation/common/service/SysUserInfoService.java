package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoResponseDTO;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoServiceRequestDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface SysUserInfoService extends IService<SysUserInfo>, UserDetailsService {
    UserDetails userLogin(SysUserInfo sysUserInfo);

    Boolean checkVerifiedCode(String verifiedCode);

    Page<SysUserInfoResponseDTO> getAllUserByList(ComplexQueryRequest<SysUserInfoServiceRequestDTO> requestDTO);

}
