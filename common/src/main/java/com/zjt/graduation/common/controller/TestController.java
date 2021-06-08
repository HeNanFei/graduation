/*
package com.zjt.graduation.common.controller;

import cn.hutool.core.util.NumberUtil;
import com.zjt.graduation.common.api.Api;
import com.zjt.graduation.common.config.mail.MailConfig;
import com.zjt.graduation.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @Author hyh
 * @Date: 2020/12/7 22:25
 * @Version 1.0
 *//*

@EnableScheduling
@RestController
public class TestController implements Api {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private MailConfig mailConfig;

    @Scheduled(cron = "0/5 * * * * ?")
    @Override
    public String get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        NumberUtil.generateRandomNumber(1,9999999,1);
        int res = mailConfig.sendEmail(new String[]{"123456@163.com","2531700373@qq.com"},//这里可以发送至任何邮箱
                "测试邮件",
                "点击这里<a href='https://www.baidu.com/'>百度一下，你就知道</a>",
                "text/html;charset=utf-8");


        System.out.println("\n发送结果:" + res);

        return String.valueOf(res);
    }

}
*/
