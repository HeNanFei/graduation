package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.permission.PermissionResponseDTO;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT * FROM (SELECT\n" +
            "\trolePermission.*,permission.`name` permissionName,url,\n" +
            "\trole. NAME roleName\n" +
            "\tFROM\n" +
            "\tRolePermission rolePermission\n" +
            "\tJOIN Permission permission\n" +
            "\tJOIN Role role ON role.id = rolePermission.roleId\n" +
            "\tAND permission.id = rolePermission.permissionId\n" +
            "\t" +
            ") t ")
    public List<RolePermissionResponseDTO> getAllPermission();

   @Select("select * from Permission t ${ew.customSqlSegment}")
   Page<PermissionResponseDTO> getPermissionByPage(Page page, @Param(Constants.WRAPPER)QueryWrapper queryWrapper);
}
