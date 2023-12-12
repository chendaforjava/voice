package com.season.voice.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 通用异常
 *
 * @author caofei
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5782968730281544562L;

	private int status = INTERNAL_SERVER_ERROR.value();

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(HttpStatus status, String message) {
		super(message);
		this.status = status.value();
	}
}
