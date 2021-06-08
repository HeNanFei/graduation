package com.zjt.graduation.common.response;

/**
 * 封装API的错误码
 * zjt 2020/11/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
