package com.season.voice.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GuideDTO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "导游名称")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String telPhone;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    @ApiModelProperty(value = "评分")
    private Double score;

    @ApiModelProperty(value = "座右铭")
    private String motto;

    @ApiModelProperty(value = "标签")
    private List<String> label;


}
