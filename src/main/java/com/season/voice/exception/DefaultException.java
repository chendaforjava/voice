package com.season.voice.exception;


import com.season.voice.constant.CommonFault;

/**
 * 默认异常
 * 
 * @author HuangHuaSheng
 * @date 2019年8月21日
 *
 */
public class DefaultException extends BaseException {

	private String message;

	private Integer code;

	public DefaultException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaultException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DefaultException(String message, Integer code) {
		this.code = code;
		this.message = message;
	}

	public DefaultException(String message) {
		this.message = message;
//		super(message);
		// TODO Auto-generated constructor stub
	}

	public DefaultException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getFaultCode() {
		return CommonFault.DEFAULT.getCode();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
