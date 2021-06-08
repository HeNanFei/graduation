package com.zjt.graduation.feign2.inter;

import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hyh
 * @Date: 2020/10/2 9:18
 * @Version 1.0
 */
@Component
@FeignClient(name = "mservice",value = "mservice")
public interface Inter {
    @RequestMapping("/test")
    public void test();

    @ApiOperation(value = "群发邮件")
    @PostMapping("/mservice/mail/sendAllUser")
    CommonResult sendUser();
}
