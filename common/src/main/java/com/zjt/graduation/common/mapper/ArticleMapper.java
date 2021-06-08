/*package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

*//**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @Author hyh
 * @since 2020-08-02
 *//*
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select * from article t ${ew.customSqlSegment}")
    Page<Article> getAll(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}*/
