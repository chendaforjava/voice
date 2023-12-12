package com.season.voice.enums;

/**
 * @author: MiKey
 * @Date: 2022/7/26 10:31
 * @Description:
 */
public enum DingTalkActionEnum {
    EXCHANGE_RATE(1, "汇率")
    ,SABRE_Q(2, "sabre清Q")
    ,OTA_ORDER_FAIL(3, "生单失败通知客服补单")
    ,OTA_CANCEL_FAIL(4, "OTA取消失败通知")
    ,SY_TH_DATA(5, "同步协议价同行失败通知")
    ,SABRE_BOOKING_EX(6, "Sabre预定失败预警")
    ,SABRE_AERIAL_CHANGE_EX(7, "航变信息推送失败通知")
    ,TRAVELFUSION_REFUNDFEE_NOTICE(8, "TF同一航司不同退改情况接收通知")
    ,BSP_TICKETING_ERROR_NOTICE(9, "BSP出票错误通知")
    ,BSP_VOID_BUILD_CONTROL_ERROR(10, "BSP作废处理后建控失败通知")
    ,CANCEL_TWICE_NEXT_DAY(11, "隔天二次取消失败通知")
    ,HTTPCLIENT_TIME_OUT(12, "调用外部接口超时预警通知")
    ;

    private Integer action;

    private String desc;

    DingTalkActionEnum(Integer action, String desc) {
        this.action = action;
        this.desc = desc;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
