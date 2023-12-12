package com.season.voice.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 健康检查异常
 */
public class HeathCheckException extends RuntimeException {

	private int status = INTERNAL_SERVER_ERROR.value();

	public HeathCheckException(String message) {
		super(message);
	}

	public HeathCheckException(HttpStatus status, String message) {
		super(message);
		this.status = status.value();
	}
}
