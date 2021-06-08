package com.zjt.graduation.common.config.exception;

import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.response.ResultCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        return CommonResult.failed(e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public CommonResult userLogin(UsernameNotFoundException e) {
        return CommonResult.failed(ResultCode.USER_LOGIN_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(value = BadCredentialException.class)
    public CommonResult userLoginError(BadCredentialException e) {
        return CommonResult.failed(ResultCode.USER_LOGIN_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(value = BeanTransFormException.class)
    public CommonResult beanTransFormError(BeanTransFormException e) {
        return CommonResult.failed(ResultCode.BEAN_TRANSFORM_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(value = NoHeaderException.class)
    public CommonResult noHeader(BeanTransFormException e) {
        return CommonResult.failed(e.getMessage());
    }





}
