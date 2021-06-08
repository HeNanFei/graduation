package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.TGood;
import com.zjt.graduation.common.mapper.TGoodMapper;
import com.zjt.graduation.common.service.TGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TGoodServiceImpl extends ServiceImpl<TGoodMapper, TGood> implements TGoodService {

    @Autowired
    private TGoodMapper tGoodMapper;


    @Override
    public Integer insertBatch(List<TGood> list) {
        return tGoodMapper.insertBatchSomeColumn(list);
    }
}
