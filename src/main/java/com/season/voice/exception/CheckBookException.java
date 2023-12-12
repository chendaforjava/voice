package com.season.voice.exception;


/**
 * 酒店试预订返回异常删除售卖缓存
 * @author peter
 */
public class CheckBookException extends BaseModelerRestException {

	private static final long serialVersionUID = 1L;

	public CheckBookException(String s) {
		super(s);
	}

	public CheckBookException(String message, String messageKey) {
	    this(message);
	    setMessageKey(messageKey);
	}

	public CheckBookException(String s, Throwable t) {
        super(s, t);
    }
}
