package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjt.graduation.common.entity.SystemClass;
import com.zjt.graduation.common.excelentity.SystemClassExcel;
import com.zjt.graduation.common.mapper.SystemClassMapper;
import com.zjt.graduation.common.service.SystemTeacherService;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 10:08
 */
public class SystemClassListner extends AnalysisEventListener<SystemClassExcel> {

    private List<SystemClassExcel> systemClassExcels;

    public SystemClassListner() {
        this.systemClassExcels = new ArrayList<>();
    }

    @Override
    public void invoke(SystemClassExcel systemClassExcel, AnalysisContext analysisContext) {
        systemClassExcels.add(systemClassExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        SystemTeacherService systemTeacherService = ApplicationContextUtils.getBean(SystemTeacherService.class);
        Map<String, Long> teacherMap = systemTeacherService.list().stream().collect(Collectors.toMap(n -> n.getTeacherName(), n -> n.getId()));

        List<SystemClass> saveClassList = new ArrayList<>();
        SystemClassMapper bean = ApplicationContextUtils.getBean(SystemClassMapper.class);
        List<SystemClass> systemClasses = bean.selectList(new QueryWrapper<>());
        Set<String> existClassName = systemClasses.stream().map(n -> n.getClassName()).collect(Collectors.toSet());
        if(!CollectionUtils.isEmpty(systemClassExcels)){
            for (int i = 0; i < systemClassExcels.size(); i++) {
                SystemClass systemClass = new SystemClass();
                BeanUtils.copyProperties(systemClassExcels.get(i),systemClass);
                if(existClassName.add(systemClass.getClassName())) {
                    systemClass.setTeacherId(teacherMap.get(systemClass.getTeacherName()));
                    saveClassList.add(systemClass);
                }
            }
            if(!CollectionUtils.isEmpty(saveClassList)){
                bean.insertBatchSomeColumn(saveClassList);
            }
        }
    }
}
