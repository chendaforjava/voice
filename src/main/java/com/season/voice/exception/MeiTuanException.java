package com.season.voice.exception;


/**
 * @author jbarrez
 */
public class MeiTuanException extends BaseModelerRestException {

	private static final long serialVersionUID = 1L;

	public MeiTuanException(String s) {
		super(s);
	}

	public MeiTuanException(String message, String messageKey) {
	    this(message);
	    setMessageKey(messageKey);
	}

	public MeiTuanException(String s, Throwable t) {
        super(s, t);
    }
}
