package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.entity.FileRecord;
import com.zjt.graduation.common.mapper.FileRecordMapper;
import com.zjt.graduation.common.service.FileRecordService;
import org.springframework.stereotype.Service;

/**
 * @Author hyh.
 * @version 1.0
 * @Date: 2021/3/20 8:22
 */
@Service
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {
}
