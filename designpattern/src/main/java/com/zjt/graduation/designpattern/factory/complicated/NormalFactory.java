package com.zjt.graduation.designpattern.factory.complicated;

public class NormalFactory extends ComplicatedFactory{
    @Override
    public School getSchool() {
        return new NormalSchool().createSchool("most of schoole");
    }

    @Override
    public Future getFuture() {
        return new NormalFuture().futrue("a common future");
    }

    @Override
    public Student getStudent() {
        return new NormalStudent().study("70% subjects");
    }
}
