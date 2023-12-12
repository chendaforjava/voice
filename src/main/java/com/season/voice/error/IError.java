package com.season.voice.error;

/**
 * 错误结构接口
 * 
 * @author HuangHuaSheng
 * @date 2019年8月12日
 *
 */
public interface IError {

	public int getApiCode();

	public int getFaultCode();

	public String getErrorId();

	public String getArgs();

	public String getMessage();
}
