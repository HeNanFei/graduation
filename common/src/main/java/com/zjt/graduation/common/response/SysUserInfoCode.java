package com.zjt.graduation.common.response;

public enum SysUserInfoCode {

    USER_NOT_FOUND(5666,"用户不存在");

    private Long code;

    private  String message;


    private SysUserInfoCode(long code, String message) {
        this.code = code;
        this.message = message;
    }


    public long getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}
