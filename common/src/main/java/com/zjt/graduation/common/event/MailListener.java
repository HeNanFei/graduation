package com.zjt.graduation.common.event;

import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.service.MailService;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 12:33
 */
@Component
public class MailListener implements ApplicationListener<InsertEvent> {

    @Async
    @Override
    public void onApplicationEvent(InsertEvent insertEvent) {
        String message = insertEvent.getMessage();
        List<SysUserInfo> sysUserInfoList = insertEvent.getSysUserInfoList();
        if(!CollectionUtils.isEmpty(sysUserInfoList)){
            List<String> emails = sysUserInfoList.stream().filter(n -> n.getEmail() != null).map(n -> n.getEmail()).collect(Collectors.toList());
            MailService mailService = ApplicationContextUtils.getBean(MailService.class);
            if(!CollectionUtils.isEmpty(emails)){
                mailService.sendMail(message,emails);
            }
        }
    }
}
