package com.zjt.graduation.common.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hyh
 * @Date: 2020/12/7 23:09
 * @Version 1.0
 */
@io.swagger.annotations.Api(tags = "测试api")
@RequestMapping("zz")
public interface Api {
    @ApiOperation(value = "tt",notes = "tt")
    @RequestMapping("/hh")
    public String get();
}
