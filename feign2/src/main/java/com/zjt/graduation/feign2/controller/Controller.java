package com.zjt.graduation.feign2.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.feign2.inter.Inter;
import com.zjt.graduation.feign2.inter.OtherInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @Author hyh
 * @Date: 2020/10/2 9:19
 * @Version 1.0
 */
@RestController
public class Controller {
    @Autowired
    private Inter inter;
    
    @Autowired
    private OtherInter otherInter;



    @RequestMapping("/test")
    public void test(){
        inter.test();
    }




    @GetMapping("/send")
    public CommonResult test2(){
        return inter.sendUser();
    }

    @GetMapping("/getConfigList")
    public String getConfigList(){
        String content = null;
        try {
            String serverAddr = "localhost";
            String dataId = "feignservice-dev6969.yaml";
            String group = "feignservice";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            properties.put("namespace","3ee24d33-9483-4f28-a2a6-6e7ee95be819");
            ConfigService configService = NacosFactory.createConfigService(properties);

            content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
    }


    
}
