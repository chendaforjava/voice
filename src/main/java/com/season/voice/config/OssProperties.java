package com.season.voice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix= OssProperties.PREFIX )
public class OssProperties
{
    public static final String PREFIX="oss";
    /**
     * 节点
     */
    private String Endpoint;

    /**
     * 阿里云AccessKeyId
     **/
    private String AccessKeyId = "";

    /**
     * 阿里云AccessKeySecret
     **/
    private String AccessKeySecret = "";

    /**
     * bucket名称
     **/
    private String BucketName = "";

    /**
     * 下载路径
     **/
    private String downloadFileUrl = "";

    /**
     * 调用方系统名称
     **/
    private String systemName = "";

    /**
     * 是否可用
     */
    private boolean enable;

}