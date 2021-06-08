/*
package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import com.zjt.graduation.serverfile.utils.ApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

*/
/**
 * @Author hyh
 * @Date: 2020/10/31 8:40
 * @Version 1.0
 *//*

public class Thread4 extends Thread{
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
        bean.dropCollection("test2");
        Integer temp  = this.countNumber;
        this.startTime = LocalDateTime.now();
            while(countNumber>3){
                synchronized (object){
                    countNumber -- ;
                    TestClazz testClazz = list.get(countNumber);

                System.out.println(Thread.currentThread().getName()+"---------"+list.get(countNumber).getName());
            }
        }
       this.endTime = LocalDateTime.now();

        Duration between = Duration.between(this.startTime, this.endTime);
        System.out.println("用时"+between.getSeconds());
        System.out.println(this.startTime+"开始时间");
        System.out.println(this.endTime+"结束时间");
        System.out.println(temp);

    }
}
*/
