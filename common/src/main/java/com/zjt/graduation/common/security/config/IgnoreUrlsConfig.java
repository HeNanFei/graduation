package com.zjt.graduation.common.security.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用于配置白名单资源路径
 * zjt 2020/11/5.
 */
/*
@Component
*/
/*@ConfigurationProperties(prefix = "secure.ignored")*/
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

    @Value("${ignore.url}")
    private String url;

    private String defaultUrl = "/webjars/**,/swagger-resources/**,/v2/**,/swagger-ui.html,/baidu.com,/webjars/springfox-swagger-ui/**,/error/**,/api/register/enterpriseUsers/**,/api/login/**,/api/refreshToken,/api/management/user/login,/mservice/sys-user-info/login,/druid/**,/fileservice/user/downloaModel,/fileservice/user/downloadFile";

    private List<String> whiteList;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public List<String> getWhiteList() {


        return Arrays.asList(this.defaultUrl.split(","));
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
