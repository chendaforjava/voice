package com.season.voice.exception;

import com.season.voice.constant.CommonFault;

/**
 * Rest访问异常
 * 
 * @author HuangHuaSheng
 * @date 2019年8月21日
 *
 */
public class RestException extends BaseException {

	public RestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getFaultCode() {
		return CommonFault.REST_CLIENT.getCode();
	}

}
