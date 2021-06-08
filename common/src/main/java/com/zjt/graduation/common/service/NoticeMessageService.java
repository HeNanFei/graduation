package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.entity.NoticeMessage;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 13:54
 */
public interface NoticeMessageService extends IService<NoticeMessage> {

   Page<NoticeMessage> getPersonNoticeMessage(Page page,String  userName);

}
