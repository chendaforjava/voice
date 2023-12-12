package com.season.voice.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.CloseOrderRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByIdRequest;
import com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.service.refund.RefundService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
@EnableAutoConfiguration
public class WxPayConfig {
    @Resource
    private WxConfig wxConfig;

//    @Bean
//    public JsapiService jsapiService(){
//        Config config = new RSAAutoCertificateConfig.Builder()
//                        .merchantId(wxConfig.getMchId())
//                        .privateKeyFromPath(wxConfig.getPrivateKeyPath())
//                        .merchantSerialNumber(wxConfig.getCertificateSerialNo())
//                        .apiV3Key(wxConfig.getApiKey())
//                        .build();
//       return new JsapiService.Builder().config(config).build();
//    }
//
//    @Bean
//    public RefundService refundService(){
//        Config config = new RSAAutoCertificateConfig.Builder()
//                        .merchantId(wxConfig.getMchId())
//                        .privateKeyFromPath(wxConfig.getPrivateKeyPath())
//                        .merchantSerialNumber(wxConfig.getCertificateSerialNo())
//                        .apiV3Key(wxConfig.getApiKey())
//                        .build();
//       return  new RefundService.Builder().config(config).build();
//    }

    @Bean
    public CloseOrderRequest orderRequest(){
        CloseOrderRequest request =  new CloseOrderRequest();
        request.setMchid(wxConfig.getMchId());
        return request;
    }

    @Bean
    public PrepayRequest prepayRequest(){
        PrepayRequest request = new PrepayRequest();
        request.setNotifyUrl(wxConfig.getNotifyUrl());
        request.setAppid(wxConfig.getAppId());
        request.setMchid(wxConfig.getMchId());
        return request;
    }

    @Bean
    public QueryOrderByIdRequest queryOrderByIdRequest(){
        QueryOrderByIdRequest request = new QueryOrderByIdRequest();
        return request;
    }

    @Bean
    public QueryOrderByOutTradeNoRequest outTradeNoRequest(){
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        return  request;
    }

}
