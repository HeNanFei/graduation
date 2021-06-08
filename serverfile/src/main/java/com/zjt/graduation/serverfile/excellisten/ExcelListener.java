package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zjt.graduation.common.excelentity.SysUserInfoExcel;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<SysUserInfoExcel> {

    private Class aClass;

    private List<SysUserInfoExcel> list;
    //private List<T> objectList;

    private Field [] fields;

    @Override
    public void invoke(SysUserInfoExcel sysUserInfo, AnalysisContext analysisContext) {
        System.out.println(sysUserInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
