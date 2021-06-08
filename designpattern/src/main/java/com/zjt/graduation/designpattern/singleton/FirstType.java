package com.zjt.graduation.designpattern.singleton;

public class FirstType {

    private FirstType(){
    }
    public static final  FirstType firstType = new FirstType();

    public static  FirstType firstType(){
        return firstType;
    }
}
