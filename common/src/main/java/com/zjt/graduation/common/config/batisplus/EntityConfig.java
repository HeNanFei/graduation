package com.zjt.graduation.common.config.batisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EntityConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("deleted", 0L,metaObject);
        this.setFieldValByName("dataCreateTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("dataCreateBy",364116L,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatetime",LocalDateTime.now(),metaObject);
        this.setFieldValByName("dataChangeBy",364116L,metaObject);

    }
}
