package com.season.voice.exception;

/**
 * 错误定义接口
 * 
 * @author HuangHuaSheng
 * @date 2019年9月2日
 *
 */
public interface FaultDef {

	/**
	 * 获取编号
	 * 
	 * @return
	 */
	int getCode();

	/**
	 * 获取错误提醒文字
	 * 
	 * @return
	 */
	String getMessage();

}
