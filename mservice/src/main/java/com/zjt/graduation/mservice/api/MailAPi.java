package com.zjt.graduation.mservice.api;

import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hyh
 * @Date: 2020/10/4 21:35
 * @Version 1.0
 */
@Api(tags = "邮件相关Api")
@RequestMapping("/mservice/mail")
public interface MailAPi {
    @ApiOperation(value = "群发邮件")
    @PostMapping("/sendAllUser")
    CommonResult sendUser();


    @ApiOperation(value = "单发邮件")
    @PostMapping("/sendEmaiBySingle")
    CommonResult sendBySingle();
}
