package com.zjt.graduation.serverfile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.controller.AbstractController;
import com.zjt.graduation.common.entity.SystemClass;
import com.zjt.graduation.common.entity.SystemStudent;
import com.zjt.graduation.common.info.StringUtils;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.search.SearchDao;
import com.zjt.graduation.common.service.SystemClassService;
import com.zjt.graduation.common.service.SystemStudentService;
import com.zjt.graduation.common.service.SystemTeacherService;
import com.zjt.graduation.serverfile.api.SystemStudentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/21 19:25
 */
@RestController
public class SystemStudentController extends AbstractController implements SystemStudentApi {

    @Autowired
    private SystemStudentService systemStudentService;

    @Autowired
    private SystemTeacherService systemTeacherService;
    
    @Autowired
    private SystemClassService systemClassService;

    @Override
    public CommonResult pageStudentData(Page page) {
        return CommonResult.success( systemStudentService.page(page,new QueryWrapper<>()));
    }

    @Override
    public CommonResult detailForStudent(Long id) {
        return CommonResult.success(systemStudentService.getById(id));
    }

    @Override
    public CommonResult deleteStudent(Long id) {
        return CommonResult.success(systemStudentService.removeById(id));
    }

    @Override
    public CommonResult searchResultPage(SearchDao searchDao) {
        Page page = new Page();
        page.setCurrent(Optional.ofNullable(searchDao.getCurrent()).orElse(1));
        page.setSize(Optional.ofNullable(searchDao.getSize()).orElse(10));
        QueryWrapper<SystemStudent> queryWrapper =getQueryBack(getUserName(),getUserType());
        if(!StringUtils.isEmpty(searchDao.getFieldValue())) {
            queryWrapper.eq(Optional.ofNullable(searchDao.getFieldName()).orElse(""), Optional.ofNullable(searchDao.getFieldValue()).orElse(""));
        }
        return CommonResult.success(systemStudentService.page(page,queryWrapper));
    }
    public  QueryWrapper<SystemStudent> getQueryBack(String username,String userType){
        QueryWrapper<SystemStudent> systemStudentQueryWrapper = new QueryWrapper<>();
        if("admin".equals(userType)){
            return systemStudentQueryWrapper;
        }
        if("teacher".equals(userType)){
            List<SystemClass> teacherClassInfo = systemClassService.list(new QueryWrapper<SystemClass>().eq("teacherName", username));
            List<Long> classId = teacherClassInfo.stream().map(n -> n.getId()).collect(Collectors.toList());
            systemStudentQueryWrapper.in("classId",classId);
            return systemStudentQueryWrapper;
        }
        if("student".equals(userType)){
            systemStudentQueryWrapper.eq("studentName",username);
            return systemStudentQueryWrapper;
        }
        return null;
    }
}
