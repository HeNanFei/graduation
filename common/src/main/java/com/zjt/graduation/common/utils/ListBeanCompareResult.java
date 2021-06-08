package com.zjt.graduation.common.utils;

import java.util.List;
import java.util.Map;

/**
 * @Author hyh
 * @Date: 2020/10/22 7:58
 * @Version 1.0
 */
public class ListBeanCompareResult<O, N> {

    private List<O> deletedValue;

    private List<N> newValue;

    Map<String, List<BeanCompareEntity>> diffEntities;

    public List<O> getDeletedValue() {
        return deletedValue;
    }

    public void setDeletedValue(List<O> deletedValue) {
        this.deletedValue = deletedValue;
    }

    public List<N> getNewValue() {
        return newValue;
    }

    public void setNewValue(List<N> newValue) {
        this.newValue = newValue;
    }

    public Map<String, List<BeanCompareEntity>> getDiffEntities() {
        return diffEntities;
    }

    public void setDiffEntities(Map<String, List<BeanCompareEntity>> diffEntities) {
        this.diffEntities = diffEntities;
    }
}
