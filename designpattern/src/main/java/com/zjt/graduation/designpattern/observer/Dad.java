package com.zjt.graduation.designpattern.observer;

public class Dad implements Observer {
    @Override
    public void doSometing(Situation situation) {
        System.out.println("dad is feeding"+"situation is"+situation.getType());
    }
}
