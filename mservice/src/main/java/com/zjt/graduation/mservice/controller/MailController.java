package com.zjt.graduation.mservice.controller;

import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.MailService;
import com.zjt.graduation.mservice.api.MailAPi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:38
 * @Version 1.0
 */
@RestController
public class MailController implements MailAPi {
    @Autowired
    private MailService mailService;

    @Override
    public CommonResult sendUser() {
        Integer integer = mailService.sendMail();
        if(integer != null){
            return CommonResult.success("send success");
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult sendBySingle() {

        Map map = mailService.sendBySingle();
        String total =(String) map.get("total");
        if(!StringUtils.isEmpty(total)){
            return CommonResult.success(String.format("本次成功发送邮件%s条",total));
        }
        return CommonResult.failed();
    }


}
