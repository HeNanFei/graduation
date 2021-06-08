package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.role.RoleResponseDTO;
import com.zjt.graduation.common.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from Role t ${ew.customSqlSegment}")
    public Page<RoleResponseDTO> getRolepage(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
