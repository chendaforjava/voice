package com.season.voice.service;

import com.season.voice.dto.WxOrderDto;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;

public interface WxPayService {

    /** 关闭订单 */
    void closeOrder(String orderNo);


    /** JSAPI支付下单 */
    PrepayResponse prepay(WxOrderDto wxOrderDto);


    /** 微信支付订单号查询订单 */
    Transaction queryOrderById();

    /** 商户订单号查询订单 */
    Transaction queryOrderByOutTradeNo();

    /** 退款申请 */
    Refund create();

    /** 查询单笔退款（通过商户退款单号） */
    Refund queryByOutRefundNo();
}
