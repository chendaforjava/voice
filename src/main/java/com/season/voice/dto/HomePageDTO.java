package com.season.voice.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.season.voice.entity.Guide;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HomePageDTO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "城市名称")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "省/洲")
    private String province;

    @ApiModelProperty(value = "首页图片地址")
    private String picUrl;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "地胆列表")
    List<GuideDTO> guideList;

    @ApiModelProperty(value = "首页推荐列表")
    List<HomePageDTO> recommendList;

}
