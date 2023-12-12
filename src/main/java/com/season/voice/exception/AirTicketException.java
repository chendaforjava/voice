package com.season.voice.exception;


import com.season.voice.enums.AirticketErrorEnum;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 通用异常
 *
 * @author ivan
 */
public class AirTicketException extends RuntimeException {

	private static final long serialVersionUID = 5782968730281544566L;

	private int errorCode = INTERNAL_SERVER_ERROR.value();

	public AirTicketException(String message) {
		super(message);
	}

	public AirTicketException(AirticketErrorEnum airticketErrorEnum, String message) {
		super(message);
		this.errorCode = airticketErrorEnum.getCode();
	}

	public int getErrorCode(){
		return this.errorCode;
	}
}
