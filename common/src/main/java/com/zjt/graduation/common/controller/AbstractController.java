package com.zjt.graduation.common.controller;

import com.zjt.graduation.common.security.utils.JwtTokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
@Component
public abstract class AbstractController {
    private final String headerName = "authorization";



    protected String getHeader(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Enumeration<String> headerNames = servletRequestAttributes.getRequest().getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headValue = headerNames.nextElement();
            if(headerName.equals(headValue)){
                return servletRequestAttributes.getRequest().getHeader(headerName);
            }
        }
        return null;
    }

    protected Long getUserId(){
        Long userId = null;
        if(StringUtils.isEmpty(getHeader())){
            return null;
        }
        userId = JwtTokenUtil.getUserId(getHeader());
        return userId;
    }

    protected String getUserName(){
        if(StringUtils.isEmpty(getHeader())){
            return null;
        }
       return JwtTokenUtil.getUserName(getHeader());
    }


    protected String getUserType(){
        String userType = null;
        if(StringUtils.isEmpty(getHeader())){
            return null;
        }
            userType = JwtTokenUtil.getUserType(getHeader());
        return userType;
    }

}
