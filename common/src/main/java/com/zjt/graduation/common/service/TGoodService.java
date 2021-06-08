package com.zjt.graduation.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.entity.TGood;

import java.util.List;

public interface TGoodService extends IService<TGood> {
    Integer insertBatch(List<TGood> list);
}
