package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.SellGood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
@Mapper
public interface GoodsMapper extends BaseMapper<SellGood> {
    Integer insertBatchSomeColumn(Collection<SellGood> entityList);


    @Update("update sellgood set number = number - #{number} where id = #{id} and deleted = 0 and number > 0 ")
    Long cutDownGoodsNumber(Integer number,Long id);
}
