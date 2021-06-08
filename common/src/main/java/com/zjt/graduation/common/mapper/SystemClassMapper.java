package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.SystemClass;

import java.util.Collection;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 9:52
 */

public interface SystemClassMapper extends BaseMapper<SystemClass> {
    Integer insertBatchSomeColumn(Collection<SystemClass> entityList);
}
