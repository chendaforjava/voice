package com.season.voice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScenicSpots {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "景点名称")
    private String scenicSpotsName;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "景点简介")
    private String introduction;

    @ApiModelProperty(value = "评分")
    private Double star;

    @ApiModelProperty(value = "景点封面图")
    private String pic_url;

    @ApiModelProperty(value = "景点详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "景点详情")
    private String detail;

    @ApiModelProperty(value = "0:不可用;1:可用")
    private Integer isEnable;

    @ApiModelProperty(value = "默认价格")
    private BigDecimal defaultPrice;

}
