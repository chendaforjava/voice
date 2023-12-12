package com.season.voice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="订单支支付对象", description="订单支支付对象")
public class WxOrderDto {

    @ApiModelProperty(value = "商品描述",required = true)
    private String description;

    @ApiModelProperty(value = "订单金额信息",required = true)
    private Amount amount;

    @ApiModelProperty(value = "支付者信息",required = true)
    private Payer payer;

}
