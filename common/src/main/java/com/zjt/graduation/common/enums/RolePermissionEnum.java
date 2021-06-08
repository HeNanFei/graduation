package com.zjt.graduation.common.enums;

import lombok.Data;

public enum RolePermissionEnum {
    DELETE_SUCCESS("删除成功");

    private String msg;

    RolePermissionEnum(String msg){
        this.msg = msg;
    }


}
