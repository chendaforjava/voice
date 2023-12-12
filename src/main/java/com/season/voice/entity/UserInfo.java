package com.season.voice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    @ApiModelProperty(value = "用户openid")
    private String openid;

    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "性别 0:男 1:女")
    private Integer gender;

    @ApiModelProperty(value = "语言区域")
    private String language;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
