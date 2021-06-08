package com.zjt.graduation.common.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hyh
 * @Date: 2020/10/2 18:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class NoHeaderException extends  RuntimeException{

    private Integer code;

    private String msg;

}
