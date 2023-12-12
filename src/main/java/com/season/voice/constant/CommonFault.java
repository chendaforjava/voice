package com.season.voice.constant;


import com.season.voice.exception.FaultDef;

/**
 * 错误码
 *
 *
 */
public enum CommonFault implements FaultDef {
    DEFAULT(1, "系统异常"), NOT_EXISTS(2, "找不到数据"), PRC(3, "服务请求结果不正确"), TIMEOUT(4, "数据库超时"), VALIDATE(6, "数据校验"),
    BREAKER(7, "服务调用异常"), JSON_CONVERT(21, "json转换出错"), REST_CLIENT(22, "rest客户端错误"), HYSTRIX(23, "服务异常");
    int code;
    String message;

    private CommonFault(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
