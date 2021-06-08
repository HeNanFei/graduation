package com.zjt.graduation.common.response;

/**
 * 枚举了一些常用API操作码
 *
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(20001, "操作成功"),
    FAILED(50001, "操作失败"),
    VALIDATE_FAILED(40004, "参数检验失败"),
    UNAUTHORIZED(40001, "暂未登录或token已经过期"),
    USER_LOGIN_ERROR(40006,"用户名密码错误"),
    BEAN_TRANSFORM_ERROR(6666,"bean转换失败"),
    NOHEADER(7888,"请重新登录"),
    INVALID_VERIFIED_CODE(43000,"验证码过期或无效"),

    FORBIDDEN(40003, "没有相关权限");



    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
