package com.zjt.graduation.designpattern.responsibilitychain.part2;

public class DreamFilter implements MyFilter{
    @Override
    public Boolean doMyfilter(Part part) {
        if(part.getContent().contains("梦想")){
            return false;
        }
        part.setContent("梦想通过");
        return true;
    }
}
