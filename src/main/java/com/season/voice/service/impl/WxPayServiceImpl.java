package com.season.voice.service.impl;

import com.season.voice.dto.WxOrderDto;
import com.season.voice.service.WxPayService;
import com.season.voice.util.SnowFlakeUtil;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.QueryByOutRefundNoRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import org.springframework.beans.BeanUtils;
import javax.annotation.Resource;

//@Service
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private JsapiService service;

    @Resource
    private CloseOrderRequest closeOrderRequest;

    @Resource
    private PrepayRequest prepayRequest;

    @Resource
    private QueryOrderByIdRequest queryOrderByIdRequest;


    @Resource
    private QueryOrderByOutTradeNoRequest queryOrderByOutTradeNoRequest;


    @Resource
    private RefundService refundService;

    @Override
    public void closeOrder(String orderNo) {
        closeOrderRequest.setOutTradeNo(orderNo);
        service.closeOrder(closeOrderRequest);
    }

    @Override
    public PrepayResponse prepay(WxOrderDto wxOrderDto) {
        Amount amount = new Amount();
        BeanUtils.copyProperties(wxOrderDto.getAmount(),amount);
        Payer payer = new Payer();
        BeanUtils.copyProperties(wxOrderDto.getPayer(),payer);
        String orderNo = String.valueOf(SnowFlakeUtil.getNextId());
        prepayRequest.setAmount(amount);
        prepayRequest.setPayer(payer);
        prepayRequest.setDescription(wxOrderDto.getDescription());
        prepayRequest.setOutTradeNo(orderNo);
        return service.prepay(prepayRequest);
    }

    @Override
    public Transaction queryOrderById() {
        return service.queryOrderById(queryOrderByIdRequest);
    }

    @Override
    public Transaction queryOrderByOutTradeNo() {
        return service.queryOrderByOutTradeNo(queryOrderByOutTradeNoRequest);
    }

    @Override
    public Refund create() {
        CreateRequest request = new CreateRequest();
        return refundService.create(request);
    }

    @Override
    public Refund queryByOutRefundNo() {
        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
        return refundService.queryByOutRefundNo(request);
    }
}
