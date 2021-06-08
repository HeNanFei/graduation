package com.zjt.graduation.serverfile.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.List;


public class ImportEvent<T> extends ApplicationContextEvent {

    private List<T> importData;

    private Class aClass;

    private Integer perSize;

    private String redisRecordKey;



    public ImportEvent(ApplicationContext source) {
        super(source);
    }

    public ImportEvent(ApplicationContext source,List<T> importData,Class clazz,Integer pers,String dataKey) {
        super(source);
        this.importData = importData;
        this.aClass = clazz;
        this.perSize = pers;
        this.redisRecordKey = dataKey;
    }

    public List<T> getImportData() {
        return importData;
    }

    public void setImportData(List<T> importData) {
        this.importData = importData;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Integer getPerSize() {
        return perSize;
    }

    public void setPerSize(Integer perSize) {
        this.perSize = perSize;
    }

    public String getRedisRecordKey() {
        return redisRecordKey;
    }

    public void setRedisRecordKey(String redisRecordKey) {
        this.redisRecordKey = redisRecordKey;
    }

    @Override
    public String toString() {
        return "ImportEvent{" +
                "importData=" + importData +
                ", aClass=" + aClass +
                ", perSize=" + perSize +
                ", redisRecordKey='" + redisRecordKey + '\'' +
                '}';
    }
}
