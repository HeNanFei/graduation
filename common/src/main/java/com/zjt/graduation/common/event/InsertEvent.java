package com.zjt.graduation.common.event;

import com.zjt.graduation.common.entity.SysUserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.List;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/22 12:20
 */
public class InsertEvent extends ApplicationContextEvent {

    private List<SysUserInfo>  sysUserInfoList;

    private String message;

    public InsertEvent(ApplicationContext source) {
        super(source);
    }


    public InsertEvent(ApplicationContext source,List<SysUserInfo> sysUserInfos,String message) {
        super(source);
        this.message = message;
        this.sysUserInfoList = sysUserInfos;
    }

    public List<SysUserInfo> getSysUserInfoList() {
        return sysUserInfoList;
    }

    public void setSysUserInfoList(List<SysUserInfo> sysUserInfoList) {
        this.sysUserInfoList = sysUserInfoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
