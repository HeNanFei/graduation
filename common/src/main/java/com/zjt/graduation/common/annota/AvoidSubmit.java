package com.zjt.graduation.common.annota;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AvoidSubmit {
    int requestTimes() default 1;
    int seconds() default 1;
}
