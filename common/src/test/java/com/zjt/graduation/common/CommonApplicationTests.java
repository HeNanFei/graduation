package com.zjt.graduation.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CommonApplicationTests {

    @Test
    void contextLoads() {
        List<Long> longs = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 99L,10L));
        List<Long> longs1 = new ArrayList<>(Arrays.asList(8L, 10L, 20L, 99L,120L));
        List<Long> longs2 = new ArrayList<>(Arrays.asList(18L, 110L, 120L, 99L));
        longs.retainAll(longs1);
        longs2.retainAll(longs1);
        longs.retainAll(longs2);
        System.out.println(longs);
    }

    @Test
    public void jjj(){
        for (int i = 0; i < 10; i++) {

        }


    }

}
