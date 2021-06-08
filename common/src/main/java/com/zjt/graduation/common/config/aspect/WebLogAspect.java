package com.zjt.graduation.common.config.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;

import com.zjt.graduation.common.service.WebLogService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private WebLogService webLogService;

    @Pointcut("execution(public * com.zjt.graduation.*.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        //LOGGER.info("返回的ret"+ret.toString());

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        Object result = null;
        if(attributes!= null) {
            request = attributes.getRequest();
            //记录请求信息
            WebLog webLog = new WebLog();
            try {
                if (joinPoint.proceed() != null) {
                    result = joinPoint.proceed();
                    Signature signature = joinPoint.getSignature();
                    MethodSignature methodSignature = (MethodSignature) signature;
                    Method method = methodSignature.getMethod();
                    if (method.isAnnotationPresent(ApiOperation.class)) {
                        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
                        webLog.setDescription(apiOperation.value());
                    }
                    long endTime = System.currentTimeMillis();
                    String urlStr = request.getRequestURL().toString();
                    webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
                    webLog.setIp(request.getRemoteUser());
                    webLog.setMethod(request.getMethod());
                    webLog.setParameter(JSONUtil.toJsonStr(getParameter(method, joinPoint.getArgs())));
                    webLog.setResult(JSONUtil.toJsonStr(result));
                    webLog.setSpendTime((int) (endTime - startTime));
                    webLog.setStartTime(startTime);
                    webLog.setUri(request.getRequestURI());
                    webLog.setUrl(request.getRequestURL().toString());
                    webLog.setId(null);
                    webLogService.save(webLog);

                    LOGGER.info("{}", JSONUtil.parse(webLog));
                }

            } catch (Exception e) {
                String urlStr = request.getRequestURL().toString();
                webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
                webLog.setIp(request.getRemoteUser());
                webLog.setMethod(request.getMethod());
                webLog.setResult(JSONUtil.toJsonStr(result));
                webLog.setStartTime(startTime);
                webLog.setUri(request.getRequestURI());
                webLog.setUrl(request.getRequestURL().toString());
                webLog.setResult(e.toString());
                webLog.setId(null);
                webLogService.save(webLog);
                e.printStackTrace();
            }
        }
        return result;

    }

    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
}
