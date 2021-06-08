package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.SystemTeacher;

import java.util.Collection;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:10
 */
public interface SystemTeacherMapper extends BaseMapper<SystemTeacher> {
    Integer insertBatchSomeColumn(Collection<SystemTeacher> entityList);

}
