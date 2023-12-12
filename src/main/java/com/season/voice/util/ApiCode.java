package com.season.voice.util;

/**
 * APIcode获取编码
 * 
 * @author HuangHuaSheng
 * @date 2019年8月12日
 *
 */
public class ApiCode {
	public static int getCode(int moduleCode, int funcNum) {
		return moduleCode * 100 + funcNum;
	}
}
