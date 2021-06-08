package com.zjt.graduation.feignm.control;

import com.zjt.graduation.common.dto.permission.PermissionRequestDTO;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.feignm.Inter.LastInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hyh
 * @Date: 2020/10/1 9:40
 * @Version 1.0
 */
@RestController
public class Controller {

    @Autowired
    private LastInter lastInter;

    @RequestMapping("last")
    public void test(@RequestBody(required = false) ComplexQueryRequest< PermissionRequestDTO > requestDTO){
        CommonResult all = lastInter.getAll(requestDTO);
        Object data = all.getData();
        System.out.println(data);
        System.out.println(555);
    }
}
