package com.zjt.graduation.designpattern.responsibilitychain.part2;

public class MainTest {
    public static void main(String[] args) {
        MyChain myChain = new MyChain();
        myChain.addFilter(new NameFilter()).addFilter(new DreamFilter());
        Part part = new Part();
        part.setName("梅西");
        part.setContent("run");

        Boolean aBoolean = myChain.doMyfilter(part);
        System.out.println("Messi run"+aBoolean);
        System.out.println(part);

        Part part2 = new Part();
        part2.setName("林加德");
        part2.setContent("我有梦想");
        Boolean aBoolean2 = myChain.doMyfilter(part2);

        System.out.println("Lingarde run"+aBoolean2);
        System.out.println(part2);

    }
}
