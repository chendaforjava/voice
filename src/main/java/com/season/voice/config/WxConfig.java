package com.season.voice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxConfig {

    private String appId;

    private String mchId;

    private String certificateSerialNo;

    private String apiKey;

    private  String privateKeyPath;

    private String notifyUrl;
}
