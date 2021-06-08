package com.zjt.graduation.feignm.Inter;

import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author hyh
 * @Date: 2020/10/1 9:40
 * @Version 1.0
 */
@Component
@FeignClient(name = "mservice",value = "mservice")
public interface LastInter {

    @ApiOperation(value = "feign测试")
    @PostMapping("/test")
    CommonResult getAll(@RequestBody(required = false) ComplexQueryRequest<PermissionRequestDTO> requestDTO);



}
