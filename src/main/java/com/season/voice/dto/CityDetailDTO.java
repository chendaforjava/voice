package com.season.voice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CityDetailDTO {

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "城市简介")
    private String introduction;

    @ApiModelProperty(value = "城市封面图")
    private String picUrl;

    @ApiModelProperty(value = "推荐景点列表")
    List<ScenicSpotsDTO> scenicSpotsRecommend;

    @ApiModelProperty(value = "地胆推荐列表")
    List<GuideDTO> guideRecommendList;

}
