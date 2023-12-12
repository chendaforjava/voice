package com.season.voice.exception;

public class NullException extends BaseException {

	public NullException(String message) {
		super(message);
	}

	@Override
	public int getFaultCode() {
		return 1;
	}

}
