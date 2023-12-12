package com.season.voice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HomePageDTO {

    @ApiModelProperty(value = "首页详情")
    private HomePageDetailDTO homePageDetail;

    @ApiModelProperty(value = "地胆列表")
    List<GuideDTO> guideList;

    @ApiModelProperty(value = "首页推荐列表")
    List<HomePageDetailDTO> recommendList;

}
