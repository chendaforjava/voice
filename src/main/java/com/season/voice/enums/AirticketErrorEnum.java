package com.season.voice.enums;

/**
 * @author: ivan
 * @Date: 2023/3/6
 * @Description:
 */
public enum AirticketErrorEnum {

    CONFIG_INFORMATION_START_AND_STOP_NO_DATA(4401, "不存在该供应商对应配置");

    private Integer code;

    private String desc;

    AirticketErrorEnum(Integer _code, String desc) {
        this.code = _code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer _val) {
        this.code = _val;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
