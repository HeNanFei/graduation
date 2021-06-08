/*
package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import com.zjt.graduation.serverfile.utils.ApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @Author hyh
 * @Date: 2020/10/31 11:53
 * @Version 1.0
 *//*

public class Thread5 extends Thread{

    @Autowired
    private ApplicationContextUtils applicationContext;

    private List<TestClazz> list;

    private LocalDateTime startTime;


    private LocalDateTime endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    private Integer countNumber;
    public List<TestClazz> getList() {
        return list;
    }

    public void setList(List<TestClazz> list) {
        this.list = list;
        this.countNumber = list.size();
    }

    private final Object object = new Object();


    @Override
    public void run() {
        MongoTemplate bean = ApplicationContextUtils.getBean(MongoTemplate.class);
        Integer total = list.size();
        Integer start = 0;
        Integer size =  5000;
        Integer count = 0;
        synchronized (object){
            if(total>0){
                    if(list.size()>500){
                        List<TestClazz> testClazzes = list.subList(start, size);
                        bean.insert(testClazzes, "test4");
                        System.out.println(Thread.currentThread().getName()+"-----"+list.size());
                        list.removeAll(testClazzes);

                    }else{
                        bean.insert(list, "test4");
                        list.removeAll(list);
                        System.out.println(Thread.currentThread().getName()+"-----"+list.size());

                    }
            }
        }
    }
}
*/
