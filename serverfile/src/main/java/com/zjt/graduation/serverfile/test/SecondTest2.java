package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hyh
 * @Date: 2020/10/31 10:45
 * @Version 1.0
 */
public class SecondTest2 {
    public static void main(String[] args) {

        List<TestClazz> testList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            TestClazz testClazz = new TestClazz();
            testClazz
                    .setMobile("mobile"+i)
                    .setName("name"+i);
            testList.add(testClazz);
        }
        LocalDateTime startTime = LocalDateTime.now();
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));

        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(Duration.between(startTime,end).getSeconds());
    }
}
