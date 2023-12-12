package com.season.voice.exception;


/**
 * @author jbarrez
 */
public class DataNoPassException extends BaseModelerRestException {

	private static final long serialVersionUID = 1L;

	public DataNoPassException(String s) {
		super(s);
	}

	public DataNoPassException(String message, String messageKey) {
	    this(message);
	    setMessageKey(messageKey);
	}

	public DataNoPassException(String s, Throwable t) {
        super(s, t);
    }
}
