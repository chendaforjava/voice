package com.season.voice.exception;

/**
 * @author caofei
 * @date 2022/01/04
 **/
public class RedissonLockException extends RuntimeException {

    private static final long serialVersionUID = -6422212844622271825L;

    public RedissonLockException(String message) {
        super(message);
    }
}
