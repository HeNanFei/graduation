package com.zjt.graduation.designpattern;

import com.zjt.graduation.designpattern.factory.complicated.ComplicatedFactory;
import com.zjt.graduation.designpattern.factory.complicated.NormalFactory;
import com.zjt.graduation.designpattern.factory.simple.CloudFactory;
import com.zjt.graduation.designpattern.factory.simple.DreamFactory;
import com.zjt.graduation.designpattern.factory.simple.HouseFactory;
import com.zjt.graduation.designpattern.singleton.FirstType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootTest
class DesignpatternApplicationTests {

    @Test
    void contextLoads() {
        FirstType firstType = FirstType.firstType();

        FirstType firstType1 = FirstType.firstType();

        System.out.println(firstType == firstType1);



    }

    @Test
    void myTest(){

        CloudFactory cloudFactory = new CloudFactory();
        DreamFactory dreamFactory = new HouseFactory();
        System.out.println(dreamFactory.createDreamFactory());
        System.out.println( cloudFactory.createDreamFactory());
    }

    @Test
    public void test33(){
        ComplicatedFactory complicatedFactory = new NormalFactory();
        System.out.println(complicatedFactory.getSchool());
        System.out.println(complicatedFactory.getStudent());
        System.out.println(complicatedFactory.getFuture());
    }

    @Test
    public void testy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\winter\\Desktop\\使用说明.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\winter\\Desktop\\xxx.zip"));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        zipOutputStream.putNextEntry(new ZipEntry("使用说明.txt"));
        StreamUtils.copy(fileInputStream,zipOutputStream);
        zipOutputStream.closeEntry();
    }


}
