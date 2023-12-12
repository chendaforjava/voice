package com.season.voice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CityDetail {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "城市简介")
    private String introduction;

    @ApiModelProperty(value = "城市封面图")
    private String picUrl;
}
