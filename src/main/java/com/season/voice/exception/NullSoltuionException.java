package com.season.voice.exception;

/**
 * @author xwn
 * @date 2022年08月06日 17:05
 */

public class NullSoltuionException extends BaseException{
    public NullSoltuionException(String message) {
        super(message);
    }

    @Override
    public int getFaultCode() {
        return 1;
    }
}
