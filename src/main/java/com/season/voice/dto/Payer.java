package com.season.voice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="支付者信息对象", description="支付者信息对象")
public class Payer {

    @ApiModelProperty(value = "用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid")
    private String openid;

}
