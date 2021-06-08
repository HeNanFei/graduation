package com.zjt.graduation.common.utils;

import com.zjt.graduation.common.security.component.DefaultUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author hyh
 * @Date: 2020/10/7 14:41
 * @Version 1.0
 */
public class SecuritContextUtils {

    private static SecurityContextHolder securityContextHolder;

    public SecuritContextUtils(){
        securityContextHolder = securityContextHolder();
    }

    public static SecurityContextHolder securityContextHolder(){
        securityContextHolder = new SecurityContextHolder();
        return securityContextHolder;
    }
    //获取Authentication
    public static Authentication getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
    //获取用户id
    public static Long getUserId(){
        DefaultUserDetails userDetails =(DefaultUserDetails) getAuthentication().getPrincipal();
        long userId = userDetails.getUserId();
        return userId;


    }
    //获取用户角色
    public static String getUserRoles(){
        DefaultUserDetails details =(DefaultUserDetails) getAuthentication().getPrincipal();
        List<String> roles = details.getRoles();
        String[] rolesArray = new String[roles.size()];
        String roleString = StringUtils.arrayToCommaDelimitedString(roles.toArray(rolesArray));
        return roleString;

    }



}
