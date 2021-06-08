/*
package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;
import javafx.collections.ArrayChangeListener;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Author hyh
 * @Date: 2020/10/31 9:32
 * @Version 1.0
 *//*

public class MainTest {
    public  void test2(){
        Thread4 thread4 = new Thread4();
        List<TestClazz> testList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            TestClazz testClazz = new TestClazz();
            testClazz
                    .setMobile("mobile"+i)
                    .setName("name"+i);
            testList.add(testClazz);
        }
        thread4.setList(testList);
        Thread thread = new Thread(thread4,"what1");
        Thread thread2 = new Thread(thread4,"what2");
        Thread thread3 = new Thread(thread4,"what3");
        Thread thread5 = new Thread(thread4,"what4");

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
    public static void main(String[] args) {

       MainTest mainTest = new MainTest();
       mainTest.test2();

    }

}
*/
