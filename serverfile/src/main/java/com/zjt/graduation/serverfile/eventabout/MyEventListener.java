package com.zjt.graduation.serverfile.eventabout;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author hyh
 * @Date: 2020/10/26 16:46
 * @Version 1.0
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("定时任务失效");
    }
}
