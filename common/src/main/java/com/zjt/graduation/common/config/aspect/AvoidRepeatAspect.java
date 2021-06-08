package com.zjt.graduation.common.config.aspect;

import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.security.utils.JwtTokenUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@Aspect
@Component
@Order(1)
public class AvoidRepeatAspect {

    private static final String AVOIDMARK = "AVOIDSUBMIT";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Pointcut("@annotation(com.zjt.graduation.common.annota.AvoidSubmit)")
    public void avoidSubmit() {
    }

    @Before("avoidSubmit()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "avoidSubmit()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        //LOGGER.info("返回的ret"+ret.toString());

    }

    @Around("avoidSubmit()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSinature = (MethodSignature) joinPoint.getSignature();
        Method currentMethod = methodSinature.getMethod();
        currentMethod.setAccessible(true);
        if(currentMethod.isAnnotationPresent(AvoidSubmit.class)){
            AvoidSubmit annotation = currentMethod.getAnnotation(AvoidSubmit.class);
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String authorization = requestAttributes.getRequest().getHeader("Authorization");
            if(StringUtils.isEmpty(authorization)){
                throw new RuntimeException("非法请求");
            }else {
                authorization = authorization.replaceAll("Bear", "");

                int requestTimes = annotation.requestTimes();
                int keepSeconds = annotation.seconds();
                String userNameFromToken = jwtTokenUtil.getUserNameFromToken(authorization);
                String currentUserLoginRepeat = AVOIDMARK+":"+userNameFromToken+currentMethod.getName();
                if(redisTemplate.opsForValue().setIfAbsent(currentUserLoginRepeat,"1",keepSeconds, TimeUnit.SECONDS)){
                    return joinPoint.proceed();
                }else {
                    Integer existRequestTimes = Integer.valueOf((String) redisTemplate.opsForValue().get(currentUserLoginRepeat));
                    if(existRequestTimes>= requestTimes){
                        throw  new RuntimeException("请勿重复请求，稍等"+keepSeconds+"秒后请再次尝试");
                    }
                    redisTemplate.opsForValue().increment(currentUserLoginRepeat,1L);
                    redisTemplate.expire(currentUserLoginRepeat,requestTimes,TimeUnit.SECONDS);
                }
            }

        }
        return joinPoint.proceed();
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
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