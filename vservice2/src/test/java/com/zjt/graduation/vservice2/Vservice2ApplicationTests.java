package com.zjt.graduation.vservice2;

import com.aliyuncs.DefaultAcsClient;
import com.zjt.graduation.vservice2.util.VideoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Vservice2ApplicationTests {

    @Autowired
    private VideoUtils videoUtils;

    @Autowired
    private DefaultAcsClient defaultAcsClient;

    @Test
    void contextLoads() throws Exception {

        //videoUtils.searchMedia(defaultAcsClient);
    }

}
