package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.NoticeMessage;
import com.zjt.graduation.common.entity.SystemStudent;
import com.zjt.graduation.common.mapper.NoticeMessageMapper;
import com.zjt.graduation.common.service.NoticeMessageService;
import com.zjt.graduation.common.service.SystemStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 13:55
 */
@Service
public class NoticeMessageServiceImpl extends ServiceImpl<NoticeMessageMapper, NoticeMessage> implements NoticeMessageService {

    @Autowired
    private NoticeMessageMapper noticeMessageMapper;

    @Autowired
    private SystemStudentService systemStudentService;


    @Override
    public Page<NoticeMessage> getPersonNoticeMessage(Page page,String userName) {
        QueryWrapper<NoticeMessage> noticeMessageQueryWrapper = new QueryWrapper<>();
        SystemStudent studentInfo = systemStudentService.getOne(new QueryWrapper<SystemStudent>().eq("studentName", userName));
        //systemStudentService.page(page,noticeMessageQueryWrapper);
        return null;
    }
}
