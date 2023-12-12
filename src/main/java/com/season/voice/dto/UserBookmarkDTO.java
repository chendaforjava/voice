package com.season.voice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBookmarkDTO {

    @ApiModelProperty(value = "用户openid")
    private String openid;

    @ApiModelProperty(value = "景点id")
    private Long attractionsId;

    @ApiModelProperty(value = "景点封面url",required = true)
    private String url;

}
