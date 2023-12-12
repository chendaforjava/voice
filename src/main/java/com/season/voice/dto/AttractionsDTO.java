package com.season.voice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttractionsDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "景点名称",required = true)
    private String name;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "地址",required = true)
    private String address;

    @ApiModelProperty(value = "星级")
    private Double star;

    @ApiModelProperty(value = "是否已收藏")
    private Boolean bookmark;

    @ApiModelProperty(value = "景点封面url",required = true)
    private String url;

}
