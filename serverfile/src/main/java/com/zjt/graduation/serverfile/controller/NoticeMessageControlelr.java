package com.zjt.graduation.serverfile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.controller.AbstractController;
import com.zjt.graduation.common.dto.notice.NoticeMessageDTO;
import com.zjt.graduation.common.entity.*;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.search.SearchDao;
import com.zjt.graduation.common.service.*;
import com.zjt.graduation.serverfile.api.NoticeMessageApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 14:03
 */
@RestController
public class NoticeMessageControlelr extends AbstractController implements NoticeMessageApi {

    @Autowired
    private NoticeMessageService noticeMessageService;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private SystemStudentService systemStudentService;

    @Autowired
    private SystemClassService systemClassService;

    @Autowired
    private SystemTeacherService systemTeacherService;

    @Autowired
    private UserDetails userDetails;


    @Override
    public CommonResult pageNoticeMessage(Page page) {
        Page<NoticeMessage> noticeMessagePage = null;

        if("student".equals(getUserType())){
           noticeMessagePage =  getStudentNotice(page,getUserName(),getUserId());
        }
        if("teacher".equals(getUserType())){
            noticeMessagePage =  getTeacherNotice(page,getUserName(),getUserId());
        }
        if("admin".equals(getUserType())){
            noticeMessagePage =  getAdminNotice(page);
        }
        return CommonResult.success(noticeMessagePage);
    }

    private Page<NoticeMessage> getTeacherNotice(Page page,String username,Long userId){

        List<Long> classId = systemClassService.list(new QueryWrapper<SystemClass>().eq("teacherName", username)).
                stream().map(n -> n.getId())
                .collect(Collectors.toList());


        QueryWrapper<NoticeMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("msgTypeId",3,1).eq("personId",userId);
        if(!CollectionUtils.isEmpty(classId)){
            queryWrapper.or().in("classId",classId);
        }
        return  noticeMessageService.page(page,queryWrapper);
    }

    private Page<NoticeMessage> getStudentNotice(Page page,String username,Long userId){
        List<SystemStudent> studentInfo = systemStudentService.list(new QueryWrapper<SystemStudent>().eq("studentName", username));
        if(!CollectionUtils.isEmpty(studentInfo)){
            List<Long> classIds = studentInfo.stream().map(n -> n.getClassId()).collect(Collectors.toList());
            QueryWrapper<NoticeMessage> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("msgTypeId",1).eq("personId",userId).or().in("classId",classIds);
            return  noticeMessageService.page(page,queryWrapper);
        }
      return null;
    }


    private Page<NoticeMessage> getAdminNotice(Page page){
        return noticeMessageService.page(page,new QueryWrapper<NoticeMessage>());
    }


    @Override
    public CommonResult detailForNoticeMessage(Long id) {
        return CommonResult.success(noticeMessageService.getById(id));
    }

    @Override
    public CommonResult deleteNoticeMessage(Long id) {
        return CommonResult.success(noticeMessageService.removeById(id));
    }

    @AvoidSubmit(requestTimes = 1,seconds = 3)
    @Override
    public CommonResult createNoticeMessage(NoticeMessageDTO noticeMessageDTO) {
        if(checekNoticeName(noticeMessageDTO.getTitle())) {

            NoticeMessage noticeMessage = new NoticeMessage();
            if (noticeMessageDTO != null) {
                BeanUtils.copyProperties(noticeMessageDTO, noticeMessage);
            }

            if (!StringUtils.isEmpty(noticeMessageDTO.getClassId()) && !"1".equals(noticeMessageDTO.getMsgTypeId())) {
                SystemClass byId = systemClassService.getById(Long.valueOf(noticeMessageDTO.getClassId()));
                if (byId != null) {
                    noticeMessage.setClassId(byId.getId());
                }
            }

            setPublishType(noticeMessage,noticeMessageDTO.getPersonName());

            noticeMessage.setCreator(getUserName());
            noticeMessage.setCreatorOid(getUserId());
            noticeMessage.setPublishDate(LocalDateTime.now().toString());
            if (noticeMessageService.save(noticeMessage)) {
                return CommonResult.success("创建成功");
            }
        }
        return CommonResult.success("success");
    }


    private Boolean checekNoticeName(String name){
        return noticeMessageService.getOne(new QueryWrapper<NoticeMessage>().eq("title",name)) == null ? true: false;
    }

    private void setPublishType(NoticeMessage noticeMessage,String name){
        if(!StringUtils.isEmpty(name)){
            SysUserInfo userInfo = sysUserInfoService.getOne(new QueryWrapper<SysUserInfo>().eq("username", name));
            noticeMessage.setPersonId(userInfo.getId());
        }
    }

    @Override
    public CommonResult getBelongNoticeMessage(SearchDao searchDao) {
        return null;
    }

    @Override
    public CommonResult getClassIdAndName() {
        String userType = getUserType();
        List<SystemClass> collect = new ArrayList<>();
        if(!StringUtils.isEmpty(userType)){
            if("admin".equals(userType)) {
               collect = systemClassService.list().stream().collect(Collectors.toList());
            }
            if("teacher".equals(userType)) {
                String userName = getUserName();
                SystemTeacher teacherName = systemTeacherService.getOne(new QueryWrapper<SystemTeacher>().eq("teacherName", userName));
                collect = systemClassService.list(new QueryWrapper<SystemClass>().eq("teacherId",teacherName.getId()));
            }

        }
        return CommonResult.success(collect);
    }

}
