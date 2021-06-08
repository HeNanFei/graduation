package com.zjt.graduation.serverfile.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author hyh
 * @Date: 2020/10/25 14:22
 * @Version 1.0
 */
public class Test {
   public static Integer number = 10;

    public void ThreadTest() {


       ThreadTest threadTest = new ThreadTest();
       threadTest.setInteger(new AtomicInteger(100));
       Thread thread = new Thread(threadTest,"test1");
        Thread thread2 = new Thread(threadTest,"test2");

        thread.start();
        thread2.start();


    }


    public static void main(String[] args) {
        Test tes = new Test();
        tes.ThreadTest();

    }
}
