package com.zjt.graduation.feign2.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author hyh
 * @Date: 2020/10/2 10:47
 * @Version 1.0
 */
@Component
@FeignClient(name = "mservice",value = "mservice")
public interface OtherInter {

}
