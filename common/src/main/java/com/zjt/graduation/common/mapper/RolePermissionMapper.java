package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    @Select("SELECT * FROM (SELECT\n" +
            "\trolePermission.*,permission.`name` permissionName,url,\n" +
            "\trole. NAME roleName\n" +
            "\tFROM\n" +
            "\tRolePermission rolePermission\n" +
            "\tJOIN Permission permission\n" +
            "\tJOIN Role role ON role.id = rolePermission.roleId\n" +
            "\tAND permission.id = rolePermission.permissionId\n" +
            "\t" +
            ") t ${ew.customSqlSegment}")
    public Page<RolePermissionResponseDTO> getRolePermissionPage(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    @Select("SELECT * FROM (SELECT\n" +
            "\trolePermission.*,permission.`name` permissionName,url,\n" +
            "\trole. NAME roleName\n" +
            "\tFROM\n" +
            "\tRolePermission rolePermission\n" +
            "\tJOIN Permission permission\n" +
            "\tJOIN Role role ON role.id = rolePermission.roleId\n" +
            "\tAND permission.id = rolePermission.permissionId\n" +
            "\t" +
            ") t where roleId = #{roleId} and deleted = 0")
    public List<RolePermissionResponseDTO> getRolePermissionByRoleid(@Param("roleId") Long roleId);
}
