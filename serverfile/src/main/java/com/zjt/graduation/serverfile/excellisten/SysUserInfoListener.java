package com.zjt.graduation.serverfile.excellisten;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zjt.graduation.common.entity.SysUserInfo;

import java.util.List;

public class SysUserInfoListener extends AnalysisEventListener<SysUserInfo> {

    private List<SysUserInfo> sysUserInfoList;


    @Override
    public void invoke(SysUserInfo sysUserInfo, AnalysisContext analysisContext) {
        System.out.println(sysUserInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
