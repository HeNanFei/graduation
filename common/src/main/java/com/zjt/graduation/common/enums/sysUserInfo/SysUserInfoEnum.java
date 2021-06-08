package com.zjt.graduation.common.enums.sysUserInfo;

public enum SysUserInfoEnum {
    USER_LOGIN_ERROR("用户名或密码错误");
    private Long code;

    private String msg;

     SysUserInfoEnum(Long code,String msg){
        this.msg = msg;
        this.code = code;
    }
    SysUserInfoEnum(String msg){
        this.msg = msg;
    }
    public String getContent(){
         return this.msg;
    }

}
