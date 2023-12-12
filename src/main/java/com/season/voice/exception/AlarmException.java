package com.season.voice.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 告警异常，抛出此异常，运维监控到发送提醒
 *
 * @author hhs
 * @createTime 2020年9月2日
 */
@Slf4j
public class AlarmException extends DefaultException {
	/** 用戶 **/
	public static final int BUSINESS_TYPE_USER = 0;
	/** 酒店 **/
	public static final int BUSINESS_TYPE_HOTEL = 1;
	/** 火车 **/
	public static final int BUSINESS_TYPE_TRAIN = 2;
	/** 机票 **/
	public static final int BUSINESS_TYPE_FLIGHT = 3;
	/** 用车 **/
	public static final int BUSINESS_TYPE_CAR = 4;
	/** 保险 **/
	public static final int BUSINESS_TYPE_INSURANCE = 6;
	/** 活动商品 **/
	public static final int BUSINESS_TYPE_MICE = 7;
	/** 订单中心 **/
	public static final int BUSINESS_TYPE_ORDER = 21;

	public static int type = 0;
	public static String mobile = null;
	public static AlarmMobile alarmMobile = null;

	private AlarmException() {

	}
	public AlarmException(String message, Throwable cause) {
		super(message, cause);
		log(message);
	}

	public AlarmException(String message) {
		super( message);
		log(message);
	}

	public AlarmException(Throwable cause) {
		super(cause);
		log(cause.getMessage());
	}
	private static String getMobileStr() {
		return (alarmMobile!=null&&alarmMobile.getAlarmMobile()!=null?alarmMobile.getAlarmMobile():mobile);
	}
	private void log(String message) {
		StringBuffer sb=new StringBuffer("{\"AlarmBusinessType\":");
		sb.append( type ).append(",\"mobile\":\"[").append(getMobileStr()).append("]\",\"errorinfo\":\"").append( message).append("\"}");
		log.error(sb.toString());
	}

}
