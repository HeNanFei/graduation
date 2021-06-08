package com.zjt.graduation.common.utils;

import com.zjt.graduation.common.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;



public class BeanComparatorUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanComparatorUtils.class);

    public static <T, M> List<BeanCompareEntity> compareBean(T t, M m, List<String> fields){
        BeanMap tMap = BeanMap.create(t);
        BeanMap mMap = BeanMap.create(m);
        return (List<BeanCompareEntity>) tMap.keySet().stream()
                .filter(key->(fields.contains(key) && !compareField(tMap.get(key), mMap.get(key))))
                .map(key->new BeanCompareEntity(key.toString(), tMap.get(key), mMap.get(key)))
                .collect(Collectors.toList());
    }

    public static <T, M> List<BeanCompareEntity> compareBean(T t, M m, Map<String, String> translateMap, List<String> fields){
        List<BeanCompareEntity> result = compareBean(t, m, fields);
//* 将字段名翻译成前端页面展示能看懂的文字

        result.forEach(item->item.setText(translateMap.get(item.getFiled())));
        return result;
    }

/**
     * 比较两个集合数据的差异性，
     * @param t
     * @param m
     * @param aggField
     * @param <O> 旧数据
     * @param <N> 新数据
     * @return*/


    public static <O, N> ListBeanCompareResult compareList(List<O> t, List<N> m, String aggField, List<String> fields){
        if(CollectionUtils.isEmpty(t) && CollectionUtils.isEmpty(m)){
            return new ListBeanCompareResult();
        }

        if(CollectionUtils.isEmpty(t) && !CollectionUtils.isEmpty(m)){
            ListBeanCompareResult result = new ListBeanCompareResult();
            result.setNewValue(m);
            result.setDeletedValue(new ArrayList(1));
            result.setDiffEntities(new HashMap<>());
            return result;
        }

        if(!CollectionUtils.isEmpty(t) && CollectionUtils.isEmpty(m)){
            ListBeanCompareResult result = new ListBeanCompareResult();
            result.setNewValue(new ArrayList(1));
            result.setDeletedValue(m);
            result.setDiffEntities(new HashMap<>());
            return result;
        }

        Map<Object, O> tMap = t.stream().collect(Collectors.toMap(item-> FieldUtils.getProtectedFieldValue(aggField, item), item->item));
        Map<Object, N> mMap = m.stream().collect(Collectors.toMap(item-> FieldUtils.getProtectedFieldValue(aggField, item), item->item));
        ListBeanCompareResult result = new ListBeanCompareResult();
        result.setDeletedValue(t.stream().filter(item-> !mMap.keySet().contains(FieldUtils.getProtectedFieldValue(aggField, item))).collect(Collectors.toList()));
        result.setNewValue(m.stream().filter(item-> !tMap.keySet().contains(FieldUtils.getProtectedFieldValue(aggField, item))).collect(Collectors.toList()));
        result.setDiffEntities(t.stream()
                .filter(item-> mMap.containsKey(FieldUtils.getProtectedFieldValue(aggField, item)) && compareBean(item, mMap.get(FieldUtils.getProtectedFieldValue(aggField, item)), fields).size()>0)
                .collect(Collectors.toMap(
                        item-> FieldUtils.getProtectedFieldValue(aggField, item),
                        item->compareBean(item, mMap.get(FieldUtils.getProtectedFieldValue(aggField, item)), fields))));
        return result;
    }

    public static <O, N> ListBeanCompareResult compareList(List<O> t, List<N> m, String aggField, Map<String, String> translateMap, List<String> fields){
        ListBeanCompareResult result = compareList(t, m, aggField, fields);
        if(CollectionUtils.isEmpty(result.getDiffEntities())){
            return result;
        }
        result.getDiffEntities().values().forEach(list->((List)list).forEach(item->{
            BeanCompareEntity entity = (BeanCompareEntity)item;
            entity.setText(translateMap.get(entity.getFiled()));
        }));
        return result;
    }


    public static void main(String[] args) {
        Article article = new Article();
        article.
                setAid(121).setContent("jklsajdf").setStatus("jjsdf").setUsername("userName");
        Article article2 = new Article();
        article.
                setAid(121).setContent("jalksjf").setStatus("jlkasjdf").setUsername("userName");
        List<BeanCompareEntity> beanCompareEntities = BeanComparatorUtils.compareBean(article, article2, null);
        System.out.println(1);
    }

    private static boolean compareField(Object oldValue,  Object newValue){
        if(oldValue == null && newValue == null){
            return true;
        }
        if(oldValue == null && newValue != null){
            return false;
        }
        if(oldValue != null && newValue == null){
            return false;
        }
        return oldValue.toString().equals(newValue.toString());
    }
}
