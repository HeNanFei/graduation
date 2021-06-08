package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.TGood;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface TGoodMapper extends BaseMapper<TGood> {
    Integer insertBatchSomeColumn(Collection<TGood> entityList);
}
