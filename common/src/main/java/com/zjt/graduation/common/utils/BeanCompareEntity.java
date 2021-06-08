package com.zjt.graduation.common.utils;

/**
 * @Author hyh
 * @Date: 2020/10/22 7:59
 * @Version 1.0
 */
public class BeanCompareEntity {

    public BeanCompareEntity() {
    }

    /**
     * 字段名称
     */
    private String filed;

    /**
     * 字段文字
     */
    private String text;

    /**
     * 原值
     */
    private Object oldValue;

    /**
     *新值
     */
    private Object newValue;

    public BeanCompareEntity(String filed, Object oldValue, Object newValue) {
        this.filed = filed;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public BeanCompareEntity(String filed, String text, Object oldValue, Object newValue) {
        this.filed = filed;
        this.text = text;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
