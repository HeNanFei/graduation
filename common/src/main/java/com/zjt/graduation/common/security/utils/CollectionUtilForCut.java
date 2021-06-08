package com.zjt.graduation.common.security.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilForCut<T> {
    public static<T> List<List<T>> cutList(Integer size,List<T> originList){
        int orgineSize = originList.size();

        if(orgineSize<size || CollectionUtils.isEmpty(originList)){
            throw  new RuntimeException("Please check the List ");
        }

        List<List<T>> resutlData = new ArrayList<>();
        boolean isZhengshu = orgineSize % 2 == 0;
        int listSubSize = orgineSize / size+1;

        List<T> tempData = null;
        for (int i = 0; i < listSubSize; i++) {
            if(i == listSubSize-1){
                if(isZhengshu){
                    break;
                }else{
                    List<T> subOne = originList.subList(i * size, orgineSize);
                    tempData = new ArrayList<>();
                    tempData.addAll(subOne);
                    resutlData.add(tempData);
                }
            }else {
                List<T> cutTempData = originList.subList((i * size), (i + 1) * size);
                tempData = new ArrayList<>();
                tempData.addAll(cutTempData);
                resutlData.add(tempData);
            }
        }
        return resutlData;
    }

    public static void main(String[] args) {
        List<String> testData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testData.add(String.valueOf(i));
        }
        List<List<String>> lists = cutList(2, testData);
        System.out.println(lists);
    }
}
