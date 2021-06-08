package com.zjt.graduation.vservice2.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.dto.source.SourceAddRequestDTO;
import com.zjt.graduation.common.entity.Source;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.mapper.SourceMapper;
import com.zjt.graduation.common.security.utils.BeanUtils;
import com.zjt.graduation.common.service.SysUserInfoService;
import com.zjt.graduation.common.utils.SecuritContextUtils;
import com.zjt.graduation.vservice2.play.PlayAuth;
import com.zjt.graduation.vservice2.service.SourceService;
import com.zjt.graduation.vservice2.service.UploadService;
import com.zjt.graduation.vservice2.upload.VideoUploadAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-10-04
 */
@Service
public class SourceServiceImpl extends ServiceImpl<SourceMapper, Source> implements SourceService {

    @Autowired
    private SourceService sourceService;

    @Autowired
    private UploadVideoImpl uploader;

    @Autowired
    private UploadVideoImpl uploadVideoImpl;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Override
    public Boolean addSource(SourceAddRequestDTO sourceAddRequestDTO) throws IOException {

        Long userId = SecuritContextUtils.getUserId();
        SysUserInfo byId = sysUserInfoService.getById(userId);
        Source source = BeanUtils.transform(Source.class,sourceAddRequestDTO);
        boolean result = false;
            
            if (sourceAddRequestDTO.getVideos() != null) {
                MultipartFile videos = sourceAddRequestDTO.getVideos();
                UploadStreamResponse uploadStreamResponse = uploadService.uploadVideo(source.getSourceName(),videos.getOriginalFilename(), videos.getInputStream());

                source.setUploadUserName(byId.getUsername())
                      .setVideoId(uploadStreamResponse.getVideoId());
                result = true;
            }

        return result;
    }

    @Override
    public Boolean addSingleSource(String sourceName, MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sourceName1 = request.getParameter("sourceName");

        UploadStreamResponse uploadStreamResponse = uploadService.uploadVideo(multipartFile.getName(), multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        String requestId = uploadStreamResponse.getRequestId();
        String videoId = uploadStreamResponse.getVideoId();



        Source source = new Source();
        source
                .setVideoId(videoId)
                .setRequestId(requestId)
                .setStatus("待审核")
                .setSourceName(sourceName1);

        boolean save = sourceService.save(source);

        return save;
    }

    @Override
    public String getUrlBack(String videoId) throws ClientException {
        DefaultAcsClient defaultAcsClient = VideoUploadAuth.initVodClient();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        String playUrl = null;
        try {
            response = PlayAuth.getPlayInfo(defaultAcsClient,videoId);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
              playUrl =  playInfo.getPlayURL();
            }
            //Base信息
        } catch (Exception e) {

        }
        return playUrl;
    }

    public static void main(String[] args) {
        String lastWord = BCrypt.hashpw("如果你根本不在意，那就这样结束了吧，真的没意思", BCrypt.gensalt());

    }

}
