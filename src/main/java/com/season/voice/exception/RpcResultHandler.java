package com.season.voice.exception;

import com.season.voice.dto.DataResult;

/**
 * 远程调用结构处理
 * 
 * @author HuangHuaSheng
 * @date 2019年9月6日
 *
 */
public class RpcResultHandler {
	/**
	 * 判断远程请求结果是否正确
	 * 
	 * @param result
	 */
	public static void handle(DataResult<?> result) {
		if (!result.isSuccess()) {
			throw new RpcException(result.getCode(), result.getMessage());
		}
	}
}
