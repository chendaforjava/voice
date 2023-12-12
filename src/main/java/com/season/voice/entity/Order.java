package com.season.voice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String OrderNumber;

    @ApiModelProperty(value = "用户openid")
    private String openid;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "订单名称")
    private String orderName;

    @ApiModelProperty(value = "预定时间段")
    private String timePeriod;

    @ApiModelProperty(value = "景点id")
    private Long scenicSpotsId;

    @ApiModelProperty(value = "成人数")
    private Integer adult;

    @ApiModelProperty(value = "儿童数")
    private Integer child;

    @ApiModelProperty(value = "出行日期")
    private Date travelDate;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal disbursements;

    @ApiModelProperty(value = "支付时间")
    private Date paymentDate;

    @ApiModelProperty(value = "具体地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "联系人手机号")
    private String contactsPhone;

    @ApiModelProperty(value = "订单状态0:未支付;1:已支付")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
