package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoResponseDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {
    @Select("select * from SysUserInfo t ${ew.customSqlSegment}")
    Page<SysUserInfoResponseDTO>  getAllUserInfoByPage(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    Integer insertBatchSomeColumn(Collection<SysUserInfo> entityList);

}
