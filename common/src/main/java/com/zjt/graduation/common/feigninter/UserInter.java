package com.zjt.graduation.common.feigninter;

import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoServiceRequestDTO;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hyh
 * @Date: 2020/10/26 19:28
 * @Version 1.0
 */


@Component
@FeignClient("mservice")
public interface UserInter {

    @ApiOperation(value = "查询用户信息")
    @PostMapping("/queryAllUser")
    CommonResult queryAllUser(@RequestBody(required = false) ComplexQueryRequest<SysUserInfoServiceRequestDTO> requestDTO);

}
