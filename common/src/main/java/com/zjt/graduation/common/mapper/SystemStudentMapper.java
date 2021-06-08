package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.SystemStudent;

import java.util.Collection;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 17:18
 */
public interface SystemStudentMapper extends BaseMapper<SystemStudent> {
    Integer insertBatchSomeColumn(Collection<SystemStudent> entityList);

}
