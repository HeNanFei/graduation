package com.zjt.graduation.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class MainTest2 {
    public static void main(String[] args) {


        Child child  = new Child();
        List<Observer> observers = new ArrayList<>();
        observers.add(new Mon());
        observers.add(new Dad());
        child.setObserverList(observers);
        child.crying(new Situation().setType(1));
    }
}
