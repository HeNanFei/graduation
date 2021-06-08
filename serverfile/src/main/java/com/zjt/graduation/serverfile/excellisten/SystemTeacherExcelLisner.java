package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.entity.SysUserRole;
import com.zjt.graduation.common.entity.SystemTeacher;
import com.zjt.graduation.common.event.InsertEvent;
import com.zjt.graduation.common.excelentity.SystemTeacherExcel;
import com.zjt.graduation.common.mapper.SysUserInfoMapper;
import com.zjt.graduation.common.mapper.SystemTeacherMapper;
import com.zjt.graduation.common.security.utils.BeanUtils;
import com.zjt.graduation.common.service.SysUserRoleService;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/18 11:07
 */

public class SystemTeacherExcelLisner extends AnalysisEventListener<SystemTeacherExcel> {

    private List<SystemTeacherExcel> systemTeacherExcels;

    public SystemTeacherExcelLisner() {
        this.systemTeacherExcels = new ArrayList<>();
    }

    @Override
    public void invoke(SystemTeacherExcel systemTeacherExcel, AnalysisContext analysisContext) {
        systemTeacherExcels.add(systemTeacherExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        List<SysUserInfo> sysUserInfos = new ArrayList<>();


        if(!CollectionUtils.isEmpty(systemTeacherExcels)) {
            List<SystemTeacher> systemTeachers = new ArrayList<>();
            SystemTeacherMapper bean = ApplicationContextUtils.getBean(SystemTeacherMapper.class);
            List<SystemTeacher> existData = bean.selectList(new QueryWrapper<>());
            Set<String> existTeacherName = existData.stream().map(n -> n.getTeacherName()).collect(Collectors.toSet());
            for (int i = 0; i < systemTeacherExcels.size(); i++) {
                SystemTeacher systemTeacher = new SystemTeacher();
                BeanUtils.copyProperties(systemTeacherExcels.get(i), systemTeacher);
                if(existTeacherName.add(systemTeacher.getTeacherName())) {
                    systemTeachers.add(systemTeacher);
                }
                SysUserInfo sysUserInfo = new SysUserInfo();
                sysUserInfo.setUsername(systemTeacher.getTeacherName());
                sysUserInfo.setSex(systemTeacher.getGender());
                sysUserInfo.setEmail(systemTeacher.getEmail());
                sysUserInfo.setPassword("123456");
                sysUserInfo.setPhone(systemTeacher.getPhone());
                sysUserInfo.setRegisTime(new Date());
                sysUserInfo.setType("teacher");
                sysUserInfos.add(sysUserInfo);
            }


            if(!CollectionUtils.isEmpty(systemTeachers)){
                bean.insertBatchSomeColumn(systemTeachers);
                SysUserInfoMapper sysUserInforMapper = ApplicationContextUtils.getBean(SysUserInfoMapper.class);
                sysUserInforMapper.insertBatchSomeColumn(sysUserInfos);
                ApplicationContext context = ApplicationContextUtils.getContext();
                context.publishEvent(new InsertEvent(context,sysUserInfos,"尊敬的老师您好，欢迎使用中小学知识共享平台，您的账号为名字，密码为123456"));
            }

            List<Long> teacherIds = sysUserInfos.stream().map(n -> n.getId()).collect(Collectors.toList());

            List<SysUserRole> sysUserRoles = new ArrayList<>();
            for (int i = 0; i < teacherIds.size(); i++) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(1300814007143809027L);
                sysUserRole.setUserId(teacherIds.get(i));
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
