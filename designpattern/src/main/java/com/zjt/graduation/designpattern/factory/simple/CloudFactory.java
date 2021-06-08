package com.zjt.graduation.designpattern.factory.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloudFactory extends DreamFactory{

    private String name;

    @Override
    public DreamFactory createDreamFactory() {

        return new CloudFactory("CloudFactory");
    }
}
