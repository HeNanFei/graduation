package com.zjt.graduation.common.mapper;

import com.zjt.graduation.common.dto.sysUserRole.SysUserRoleResponseDTO;
import com.zjt.graduation.common.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Select("select * from (SELECT\n" +
            "\tsysUserRole.id,sysUserRole.deleted,sysUserRole.roleId,sysUserRole.userId,sysUserInfo.username,role.`name` roleName\n" +
            "FROM\n" +
            "\tSysUserRole sysUserRole\n" +
            "\t JOIN Role role\n" +
            "\t JOIN SysUserInfo sysUserInfo ON sysUserRole.roleId = role.id \n" +
            "\tAND sysUserRole.userId = sysUserInfo.id) t where userId = #{id}")
    public List<SysUserRoleResponseDTO> getSysUserRoleByUserId(@Param("id") Long id);

}
