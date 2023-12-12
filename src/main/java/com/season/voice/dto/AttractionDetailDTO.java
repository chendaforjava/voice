package com.season.voice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class AttractionDetailDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "景区id",required = true)
    private Long scenicSpotId;

    @ApiModelProperty(value = "景点名称",required = true)
    private String name;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "详情")
    private String detail;

//    @ApiModelProperty(value = "图片封面图")
//    private String coverUrl;
//
//    @ApiModelProperty(value = "图片url")
//    private String url;

    @ApiModelProperty(value = "图片列表")
    List<String> urlList;

    @ApiModelProperty(value = "是否已收藏")
    private Boolean bookmark;

    @ApiModelProperty(value = "星级")
    private Double star;

}
