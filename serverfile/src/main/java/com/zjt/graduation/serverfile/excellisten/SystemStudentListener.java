package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjt.graduation.common.entity.*;
import com.zjt.graduation.common.event.InsertEvent;
import com.zjt.graduation.common.excelentity.SystemStudentExcel;
import com.zjt.graduation.common.mapper.SysUserInfoMapper;
import com.zjt.graduation.common.mapper.SystemStudentMapper;
import com.zjt.graduation.common.service.SysUserRoleService;
import com.zjt.graduation.common.service.SystemClassService;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:17
 */
public class SystemStudentListener extends AnalysisEventListener<SystemStudentExcel> {

    private List<SystemStudentExcel> systemStudentExcels;

    public SystemStudentListener(){
        this.systemStudentExcels = new ArrayList<>();
    }


    @Override
    public void invoke(SystemStudentExcel systemStudentExcel, AnalysisContext analysisContext) {
        systemStudentExcels.add(systemStudentExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        SystemClassService systemClassService = ApplicationContextUtils.getBean(SystemClassService.class);
        List<SystemClass> existClass = systemClassService.list();
        Map<String, Long> classMap = existClass.stream().collect(Collectors.toMap(n -> n.getClassName(), n -> n.getId()));
        List<SystemStudent> list = new ArrayList<>();
        SystemStudentMapper bean = ApplicationContextUtils.getBean(SystemStudentMapper.class);
        Set<String> existStudentSet = bean.selectList(new QueryWrapper<>()).stream().map(n -> n.getStudentName()).collect(Collectors.toSet());

        List<SysUserInfo> sysUserInfos = new ArrayList<>();

        if(!CollectionUtils.isEmpty(systemStudentExcels)){
            for (int i = 0; i < systemStudentExcels.size(); i++) {
                SystemStudent systemStudent = new SystemStudent();
                BeanUtils.copyProperties(systemStudentExcels.get(i),systemStudent);
                if(existStudentSet.add(systemStudent.getStudentName())) {
                    systemStudent.setClassId(classMap.get(systemStudent.getClassName()));
                    list.add(systemStudent);
                }
                SysUserInfo sysUserInfo = new SysUserInfo();
                sysUserInfo.setUsername(systemStudent.getStudentName());
                sysUserInfo.setSex(systemStudent.getGender());
                sysUserInfo.setEmail(systemStudent.getEmail());
                sysUserInfo.setPassword("123456");
                sysUserInfo.setPhone(systemStudent.getPhone());
                sysUserInfo.setRegisTime(new Date());
                sysUserInfo.setType("student");
                sysUserInfos.add(sysUserInfo);

            }
           if(!CollectionUtils.isEmpty(list)){
               bean.insertBatchSomeColumn(list);
               SysUserInfoMapper sysUserInfoMapper = ApplicationContextUtils.getBean(SysUserInfoMapper.class);
               sysUserInfoMapper.insertBatchSomeColumn(sysUserInfos);
               ApplicationContext context = ApplicationContextUtils.getContext();
               context.publishEvent(new InsertEvent(context,sysUserInfos,"同学您好，欢迎注册中小学知识共享平台，账号已下发"));
           }
           //分配角色
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            List<Long> studentIds = sysUserInfos.stream().map(n -> n.getId()).collect(Collectors.toList());
            for (int i = 0; i < studentIds.size(); i++) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(1300814007143809026L);
                sysUserRole.setUserId(studentIds.get(i));
                sysUserRoles.add(sysUserRole);
            }
            SysUserRoleService sysUserRoleService = ApplicationContextUtils.getBean(SysUserRoleService.class);
            List<SysUserRole> collect = sysUserRoles.stream().filter(n -> !StringUtils.isEmpty(n.getUserId())).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(collect)) {
                sysUserRoleService.saveBatch(sysUserRoles);
            }
        }
    }
}
