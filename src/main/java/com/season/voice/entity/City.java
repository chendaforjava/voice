package com.season.voice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class City {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "国家编码")
    private String nationCode;
}
