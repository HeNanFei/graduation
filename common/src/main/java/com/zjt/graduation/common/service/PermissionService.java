package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.dto.permission.PermissionResponseDTO;
import com.zjt.graduation.common.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Mapper
public interface PermissionService extends IService<Permission> {
    Page<PermissionResponseDTO> getAllPermissionPage(ComplexQueryRequest<PermissionRequestDTO> requestDTOComplexQueryRequest);

    List<PermissionResponseDTO> getAllPermissionWithoutPage();
}
