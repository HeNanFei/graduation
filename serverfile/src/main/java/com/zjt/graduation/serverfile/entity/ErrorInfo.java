package com.zjt.graduation.serverfile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo implements Serializable {
    private Integer rowIndex;

    private String errorInfo;
}
