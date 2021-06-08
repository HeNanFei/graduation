package com.zjt.graduation.designpattern.observer;

public class Mon implements Observer{

    @Override
    public void doSometing(Situation situation) {
        System.out.println("Mon start feeding"+situation.getType());
    }
}
