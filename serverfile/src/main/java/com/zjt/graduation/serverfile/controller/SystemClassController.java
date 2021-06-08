package com.zjt.graduation.serverfile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.info.StringUtils;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.search.SearchDao;
import com.zjt.graduation.common.service.SystemClassService;
import com.zjt.graduation.serverfile.api.SystemClassApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/21 19:25
 */
@RestController
public class SystemClassController implements SystemClassApi {

    @Autowired
    private SystemClassService systemClassService;


    @Override
    public CommonResult pageClassData(Page page) {
        return CommonResult.success( systemClassService.page(page,new QueryWrapper<>()));
    }

    @Override
    public CommonResult detailForClass(Long id) {
        return CommonResult.success(systemClassService.getById(id));
    }

    @Override
    public CommonResult deleteClass(Long id) {
        return CommonResult.success(systemClassService.removeById(id));
    }

    @Override
    public CommonResult searchResultPage(SearchDao searchDao) {
        Page page = new Page();
        page.setCurrent(Optional.ofNullable(searchDao.getCurrent()).orElse(1));
        page.setSize(Optional.ofNullable(searchDao.getSize()).orElse(10));
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(searchDao.getFieldValue())) {

            queryWrapper.eq(Optional.ofNullable(searchDao.getFieldName()).orElse(""), Optional.ofNullable(searchDao.getFieldValue()).orElse(""));

        }
        return CommonResult.success(systemClassService.page(page,queryWrapper));
    }
}
