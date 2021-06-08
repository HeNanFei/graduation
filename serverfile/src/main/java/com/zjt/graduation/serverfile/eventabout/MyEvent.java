package com.zjt.graduation.serverfile.eventabout;

import org.springframework.context.ApplicationEvent;

/**
 * @Author hyh
 * @Date: 2020/10/26 16:42
 * @Version 1.0
 */
public class MyEvent extends ApplicationEvent {



    public MyEvent(Object source) {
        super(source);
    }
}
