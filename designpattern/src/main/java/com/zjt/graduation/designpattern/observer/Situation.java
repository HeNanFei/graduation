package com.zjt.graduation.designpattern.observer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Situation {
    private Integer type;
}
