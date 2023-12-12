package com.season.voice.exception;

/**
 * 流程异常抽象类
 * 
 * @author HuangHuaSheng
 * @date 2019年8月7日
 *
 */
public abstract class BaseException extends RuntimeException {
	/**
	 * 错误定义
	 */
	protected int faultCode;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, int faultCode) {
		super(message);
		this.faultCode = faultCode;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(Throwable cause, int faultCode) {
		super(cause);
		this.faultCode = faultCode;
	}

	public BaseException(Throwable cause, String message, int faultCode) {
		super(message, cause);
		this.faultCode = faultCode;
	}

	/**
	 * 获取故障编码
	 * 
	 * @return
	 */
	public abstract int getFaultCode();

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
