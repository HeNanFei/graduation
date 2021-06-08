package com.zjt.graduation.common.utils;

import com.zjt.graduation.common.entity.Article;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hyh
 * @Date: 2020/10/3 21:38
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Article> list1 = new ArrayList<>();
        List<Article> list2 = new ArrayList<>();
        list1.add(new Article().setUsername("小红").setTitle("title"));
        list1.add(new Article().setUsername("小张").setTitle("小张"));
        list1.add(new Article().setUsername("小名").setTitle("小名").setAttribute("a"));

        list1.add(new Article().setClick(1));


        list2.add(new Article().setUsername("小红").setTitle("ya"));
        list2.add(new Article().setUsername("小明").setTitle("小名").setAttribute("a"));
        list2.add(new Article().setClick(1));

        getDiffer(list1,list2,Article.class);




    }
    private static<T,M>  List<T>  getDiffer(List<T> source,List<T> update,Class clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Map map = new HashMap();
        T o = (T) clazz.newInstance();
        List<T> changeList = new ArrayList<>();
        for (int i = 0; i < update.size(); i++) {
            if(!source.contains(update.get(i))){
                changeList.add(update.get(i));
                //获取field
            }
        }
        for (T m:changeList) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field:declaredFields) {
                field.setAccessible(true);
                if(map.get(field.getName())== null){
                    List fieldList = new ArrayList();
                    if (field.get(m) != null) {
                        fieldList.add(field.get(m));
                    }
                    map.put(field.getName(),fieldList);
                }else{
                    List existList =(List) map.get(field.getName());
                    if (field.get(m) != null) {
                        existList.add(field.get(m));
                    }

                }

            }
        }
        System.out.println(map);
        return changeList;
    }


}
