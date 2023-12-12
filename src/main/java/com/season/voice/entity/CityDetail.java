package com.season.voice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("city_detail")
public class CityDetail {

    @TableId(
            value = "city_code",
            type = IdType.ASSIGN_ID
    )
    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "城市简介")
    private String introduction;

    @ApiModelProperty(value = "城市封面图")
    private String picUrl;
}
