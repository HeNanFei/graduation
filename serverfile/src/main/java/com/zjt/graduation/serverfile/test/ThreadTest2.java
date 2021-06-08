package com.zjt.graduation.serverfile.test;

/**
 * @Author hyh
 * @Date: 2020/10/29 23:34
 * @Version 1.0
 */
public class ThreadTest2 implements Runnable{

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void run() {

        System.out.println(number+"线程2");
    }
}
