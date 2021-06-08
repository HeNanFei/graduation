package com.zjt.graduation.designpattern.factory.complicated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalStudent extends Student{
    private String subject;

    @Override
    public Student study(String subject) {
        return new NormalStudent(subject);
    }
}
