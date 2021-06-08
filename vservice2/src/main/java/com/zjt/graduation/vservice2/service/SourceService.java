package com.zjt.graduation.vservice2.service;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjt.graduation.common.dto.source.SourceAddRequestDTO;
import com.zjt.graduation.common.entity.Source;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @Author hyh
 * @since 2020-10-04
 */
public interface SourceService extends IService<Source> {

    Boolean addSource(@RequestBody  SourceAddRequestDTO sourceAddRequestDTO) throws IOException;

    Boolean addSingleSource(String sourceName, MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) throws IOException;

    String getUrlBack(String videoId) throws ClientException;
}
