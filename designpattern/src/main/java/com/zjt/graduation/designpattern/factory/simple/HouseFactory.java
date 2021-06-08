package com.zjt.graduation.designpattern.factory.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseFactory extends DreamFactory{

    private String factoryName;

    @Override
    public DreamFactory createDreamFactory() {
        return new HouseFactory("the house");
    }
}
