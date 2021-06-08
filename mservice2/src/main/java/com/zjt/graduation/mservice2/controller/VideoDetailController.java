package com.zjt.graduation.mservice2.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 视频信息表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
public class VideoDetailController {
    @RequestMapping("/test")
    public void test(){
        System.out.println("'88888888888'");
        System.out.println("video test in" +
                "mservice");
    }
}

