package com.zjt.graduation.common.service;

import com.zjt.graduation.common.entity.VideoDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频信息表 服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
public interface VideoDetailService extends IService<VideoDetail> {
    Boolean uploadService(MultipartFile multipartFile);
}
