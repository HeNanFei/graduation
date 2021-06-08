package com.zjt.graduation.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.entity.NoticeMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 13:54
 */
public interface NoticeMessageMapper extends BaseMapper<NoticeMessage> {

    @Select("select * from notice_message t ${ew.customSqlSegment}")
    Page<NoticeMessage> getPersonNotice(Page page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
