/*
package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

*/
/**
 * @Author hyh
 * @Date: 2020/10/31 11:03
 * @Version 1.0
 *//*

public class ExcutorPoolTest {

    private List<TestClazz> testClazzList;

    public List<TestClazz> getTestClazzList() {
        return testClazzList;
    }

    public void setTestClazzList(List<TestClazz> testClazzList) {
        this.testClazzList = testClazzList;
    }

    public void test(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                9,
                15,
                50000L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30),
                new ThreadPoolExecutor.CallerRunsPolicy());


        Thread5 thread4 = new Thread5();


        List<TestClazz> testList = new ArrayList<>();
       */
/* for (int i = 0; i < 30000; i++) {
            TestClazz testClazz = new TestClazz();
            testClazz
                    .setMobile("mobile"+i)
                    .setName("name"+i);
            testList.add(testClazz);
        }*//*

        testList = testClazzList;
        thread4.setList(testList);
        LocalDateTime startTime = LocalDateTime.now();

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(thread4,"Thread--"+i);
            executor.execute(thread);
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("用时"+"++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Duration.between(startTime,end).getSeconds());
    }


}
*/
