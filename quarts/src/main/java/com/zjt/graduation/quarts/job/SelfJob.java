package com.zjt.graduation.quarts.job;

import cn.hutool.core.util.StrUtil;
import com.zjt.graduation.common.utils.ApplicationContextUtils;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@Component
@DependsOn("applicationContextUtils")
public class SelfJob extends AbstractJob{
    
    @Override
    public void doTask(JobExecutionContext context, JobDetail jobDetail) {
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
            if(StrUtil.isEmpty((String)jobDataMap.get("para"))){
                doTaskWithoutParam(jobDataMap);
            }else{
                doTaskWithParam(jobDataMap);
            }
        }

    public static void doTaskWithoutParam(JobDataMap jobDataMap){
            try {
                Class className = Class.forName((String) jobDataMap.get("className"));
                Method methodName = className.getDeclaredMethod((String) jobDataMap.get("methodName"));
                Object bean = ApplicationContextUtils.getBean(className);
                methodName.invoke(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static  void doTaskWithParam(JobDataMap jobDataMap){
        try {
            List<Object[]> para = getMethodParams((String) jobDataMap.get("para"));
            Class clazz = Class.forName((String) jobDataMap.get("className"));
            Class[] methodParamsValue = getMethodParams(para);
            Method method = clazz.getDeclaredMethod((String) jobDataMap.get("methodName"),methodParamsValue);
            Object bean = ApplicationContextUtils.getBean(clazz);
            method.invoke(bean,getMethodParamsValue(para));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数列表
     */
    public static Class[] getMethodParams(List<Object[]> methodParams)
    {
        Class[] classs = new Class[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams)
        {
            classs[index] = (Class) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams)
    {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams)
        {
            classs[index] = (Object) os[0];
            index++;
        }
        return classs;
    }

    public static List<Object[]> getMethodParams(String param)
    {
        if (StrUtil.isEmpty(param))
        {
            return null;
        }
        String[] methodParams = param.split(",");
        List<Object[]> classs = new LinkedList<>();
        for (int i = 0; i < methodParams.length; i++)
        {
            String str = StringUtils.trimWhitespace(methodParams[i]);
            // String字符串类型，包含'
            if (StrUtil.containsAny(str,"'"))
            {
                classs.add(new Object[] { StrUtil.replace(str, "'", ""), String.class });
            }
            // boolean布尔类型，等于true或者false
            else if (StrUtil.equalsIgnoreCase(str, "true") || StrUtil.equalsIgnoreCase(str, "false"))
            {
                classs.add(new Object[] { Boolean.valueOf(str), Boolean.class });
            }
            // long长整形，包含L
            else if (StrUtil.containsIgnoreCase(str, "L"))
            {
                classs.add(new Object[] { Long.valueOf(StrUtil.replaceIgnoreCase(str, "L", "")), Long.class });
            }
            // double浮点类型，包含D
            else if (StrUtil.containsIgnoreCase(str, "D"))
            {
                classs.add(new Object[] { Double.valueOf(StrUtil.replaceIgnoreCase(str, "D", "")), Double.class });
            }
            // 其他类型归类为整形
            else
            {
                classs.add(new Object[] { Integer.valueOf(str), Integer.class });
            }
        }
        return classs;
    }

}

