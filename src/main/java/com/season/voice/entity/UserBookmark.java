package com.season.voice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBookmark {

    @ApiModelProperty(value = "用户openid")
    private String openid;

    @ApiModelProperty(value = "景点id")
    private Long attractionsId;
}
