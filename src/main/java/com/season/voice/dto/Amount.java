package com.season.voice.dto;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="订单金额信息对象", description="订单金额信息对象")
public class Amount {

    @ApiModelProperty(value = "订单总金额，单位为分")
    private Integer total;

    @ApiModelProperty(value = "人民币，境内商户号仅支持人民币。示例值：CNY")
    private String currency;

}
