package com.zjt.graduation.serverfile.test;

import com.zjt.graduation.serverfile.clazz.TestClazz;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @Author hyh
 * @Date: 2020/10/29 23:19
 * @Version 1.0
 */
public class ThreadTest extends Thread {

    public  AtomicInteger  integer = new AtomicInteger(50);
      //Integer integer = 100;

    private Object object = new Object();

    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }

    @Override
    public void run() {

            while(integer.intValue() > 2) {
                synchronized (object) {
                integer.decrementAndGet();
                System.out.println(Thread.currentThread().getName()+"-----------------------" + integer);
            }

        }

    }
}
