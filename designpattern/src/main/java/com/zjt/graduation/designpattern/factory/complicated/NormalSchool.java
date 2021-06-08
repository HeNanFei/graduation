package com.zjt.graduation.designpattern.factory.complicated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalSchool extends School{
    private String schoolName;
    @Override
    public School createSchool(String schoolName) {
        return new NormalSchool(schoolName);
    }
}
