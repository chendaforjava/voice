
package com.season.voice.exception;

import com.season.voice.constant.CommonFault;

/**
 * 
 * @author HuangHuaSheng
 * @date 2019年9月6日
 *
 */
public class RpcException extends BaseException {
	int faultCode;

	@Override
	public int getFaultCode() {
		return CommonFault.PRC.getCode();
	}

	public RpcException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RpcException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RpcException(int fulatCode, String message) {
		super(message);
		this.faultCode = faultCode;
	}

	public RpcException(Throwable cause, int faultCode) {
		super(cause, faultCode);
		// TODO Auto-generated constructor stub
	}

	public RpcException(Throwable cause, String message, int faultCode) {
		super(cause, message, faultCode);
		// TODO Auto-generated constructor stub
	}

	public RpcException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage() + "," + faultCode;
	}

}
