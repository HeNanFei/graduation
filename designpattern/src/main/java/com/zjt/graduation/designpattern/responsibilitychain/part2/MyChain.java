package com.zjt.graduation.designpattern.responsibilitychain.part2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyChain implements MyFilter{
     private List<MyFilter> list = new ArrayList<>();

     public MyChain addFilter(MyFilter myFilter){
         list.add(myFilter);
         return this;
     }
    @Override
    public Boolean doMyfilter(Part part) {
        for (MyFilter myFilter: list) {
            if(!myFilter.doMyfilter(part)) {
                return false;
            }
        }
        return true;
    }
}
