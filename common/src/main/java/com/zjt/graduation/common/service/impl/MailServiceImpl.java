package com.zjt.graduation.common.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.zjt.graduation.common.config.mail.MailConfig;
import com.zjt.graduation.common.service.MailService;
import com.zjt.graduation.common.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:04
 * @Version 1.0
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private SysUserInfoService sysUserInfoService;
    //邮件群发用户
    @Override
    public Integer sendMail() {
        List<String> address = new ArrayList<>();
        sysUserInfoService.list().stream().filter(n -> n.getEmail() != null).forEach(n->address.add(n.getEmail()));
        //new Array
        String[] emailAddress = new String[address.size()];
        String[] realAddress = address.toArray(emailAddress);
        int[] number = NumberUtil.generateRandomNumber(1, 9999, address.size());
        Integer reuslt = mailConfig.sendEmail(realAddress, "短信验证码", "您的短信验证码为" + number[0], "text/plain;charset=utf-8");

        return reuslt;
    }



    @Override
    public Map sendBySingle() {

        List<String> address = new ArrayList<>();
        sysUserInfoService.list().stream().filter(n -> n.getEmail() != null).forEach(n->address.add(n.getEmail()));
        //new Array
        String[] emailAddress = new String[address.size()];
        String[] realAddress = address.toArray(emailAddress);

        Map reuslt = mailConfig.sendEmaiBySingle(realAddress, "短信验证码", "您的短信验证码为%s", "text/plain;charset=utf-8");
        return reuslt;
    }

    @Override
    public Integer sendMail(String message,List<String> emails) {

        //new Array
        String[] emailAddress = new String[emails.size()];
        String[] realAddress = emails.toArray(emailAddress);
        int[] number = NumberUtil.generateRandomNumber(1, 9999, emails.size());
        Integer reuslt = mailConfig.sendEmail(realAddress, message, "您的密码为123456，账号为本人名字，在知识的海洋里荡漾吧", "text/plain;charset=utf-8");

        return reuslt;
    }
}
