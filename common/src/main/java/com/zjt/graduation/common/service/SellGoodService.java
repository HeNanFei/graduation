package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.entity.SellGood;

import java.util.List;

public interface SellGoodService extends IService<SellGood> {

    public Integer insertBatch(List<SellGood> list) ;

    public String  purchase(Long id,Integer number,Long userId);
}
