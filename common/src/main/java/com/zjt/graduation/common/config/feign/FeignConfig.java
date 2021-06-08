/*
package com.zjt.graduation.common.config.feign;

import com.zjt.graduation.common.config.exception.NoHeaderException;
import com.zjt.graduation.common.response.IErrorCode;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * @Author hyh
 * @Date: 2020/10/2 18:13
 * @Version 1.0
 *//*


@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        try{
            requestTemplate.header("Authorization", request.getHeader("Authorization"));
        }catch (Exception e){
            throw new NoHeaderException();
        }
    }
}
*/
