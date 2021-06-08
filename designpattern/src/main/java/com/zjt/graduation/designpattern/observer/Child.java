package com.zjt.graduation.designpattern.observer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Child {
    private List<Observer> observerList = new ArrayList<>();

    public void crying(Situation situation){
        for (Observer o:observerList) {
            o.doSometing(situation);
        }
    }
}
