package com.zjt.graduation.designpattern.responsibilitychain.part2;

public class NameFilter implements MyFilter{
    @Override
    public Boolean doMyfilter(Part part) {
        if(part == null || part.getName().equals("马拉多纳")){
            return false;
        }
        part.setName("you are not"+part.getName());
        return true;
    }
}
