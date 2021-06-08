/*
package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import com.zjt.graduation.serverfile.utils.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;

import java.security.Permission;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ThreadCallable implements Callable {

    public List<TestClazz> testClazzList;

    private CountDownLatch countDownLatch;

    public ThreadCallable(List<TestClazz> testClazzes,CountDownLatch countDownLatchTwo){
        this.countDownLatch = countDownLatchTwo;
        this.testClazzList = testClazzes;
    }

    @Override
    public Object call() throws Exception {
        testClazzList.parallelStream().forEach(n -> System.out.println(n+Thread.currentThread().getName()));
        countDownLatch.countDown();
        return null;
    }
}
*/
