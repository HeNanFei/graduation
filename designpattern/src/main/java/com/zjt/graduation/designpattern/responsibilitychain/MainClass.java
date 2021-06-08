package com.zjt.graduation.designpattern.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        ChainList chainList = new ChainList();
        chainList.add(new SensitivceChain()).add(new ChainChapter());
        String msg = "我爱足球";
        //System.out.println(chainList.doFilter(msg));


        ChainList chainList2 = new ChainList();
        chainList2.add(new FootBallReplacer());
        chainList2.add(chainList);
        System.out.println(chainList2.doFilter(msg));


    }
}


interface  Chain{
    String doFilter(String msg);
}

class SensitivceChain implements  Chain{
    @Override
    public String doFilter(String msg) {
        return msg.replace("我","你");
    }
}


class ChainChapter implements  Chain{
    @Override
    public String doFilter(String msg) {
        return msg.replace("爱","想");
    }
}

class FootBallReplacer implements  Chain{

    @Override
    public String doFilter(String msg) {
        return msg.replace("足球","未来的自己");
    }
}

class ChainList implements Chain {
    private List<Chain> chainList = new ArrayList<>();

    public ChainList add(Chain chain){
        chainList.add(chain);
        return this;
    }

    @Override
    public String doFilter(String msg){
        for (Chain chain: chainList) {
            msg = chain.doFilter(msg);
        }
        return msg;
    }
}




