package com.zjt.graduation.mservice.controller;


import com.zjt.graduation.common.entity.Menu;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.mservice.api.MenuApi;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@RestController
@RequestMapping("/mservice/menu")
public class MenuController implements MenuApi {

    @Override
    public CommonResult getAll(ComplexQueryRequest<Menu> requestDTO) {
        return null;
    }

    @Override
    public CommonResult deleteMenuByid(Long id) {
        return null;
    }

    @Override
    public CommonResult updateMenu(Menu menu) {
        return null;
    }

    @Override
    public CommonResult addMenu(Menu menu) {
        return null;
    }
}

