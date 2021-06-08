package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjt.graduation.common.entity.TGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TGoodsMapper extends BaseMapper<TGoods> {
    @Select("select  distinct(platform_goods_code) from tgoods ")
    List<String> getColumn(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(goods_code) from tgoods ")
    List<String> getGoodsCode(@Param("ew") QueryWrapper queryWrapper);


    @Select("select  distinct(name) from tgoods ")
    List<String> getName(@Param("ew") QueryWrapper queryWrapper);



    @Select("select  distinct(title) from tgoods ")
    List<String> getTitle(@Param("ew") QueryWrapper queryWrapper);


    @Select("select  distinct(brand_code) from tgoods ")
    List<String> getBrandCode(@Param("ew") QueryWrapper queryWrapper);


    @Select("select  distinct(brand_name) from tgoods ")
    List<String> getBrandName(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(category_code) from tgoods ")
    List<String> getCategoryCode(@Param("ew") QueryWrapper queryWrapper);


    @Select("select  distinct(category_name) from tgoods ")
    List<String> getCategoryName(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(second_category_code) from tgoods ")
    List<String> getSecondCategoryCode(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(second_category_name) from tgoods ")
    List<String> getSecondCategoryName(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(three_category_code) from tgoods ")
    List<String> getThreeCategoryCode(@Param("ew") QueryWrapper queryWrapper);

    @Select("select  distinct(three_category_name) from tgoods ")
    List<String> getThreeCategoryName(@Param("ew") QueryWrapper queryWrapper);

}
