package com.season.voice.dto;


import com.season.voice.constant.CommonFault;
import com.season.voice.error.IError;

import java.util.UUID;


/*import brave.Tracing;*/

/**
 * 响应数据工具类
 *
 * @author hhs
 * @date 2019年5月27日
 *
 */
public class R {
	public static <T> DataResult<T> success() {
		return new DataResult("操作成功");
	}

	public static <T> DataResult<T> success(T data) {
		return new DataResult<T>("操作成功", data);
	}

	public static <T> DataResult<T> error(String msg) {
		DataResult dr = new DataResult<>(CommonFault.DEFAULT.getCode(), msg);
		String log_trace = null;
		/*Tracing tracing = null; //注释
		try {
			tracing = SpringContextUtils.getBean(Tracing.class);
			log_trace = tracing.tracer().currentSpan().context().spanIdString();
		} catch (Exception ex2) {
		}*/
		if (log_trace != null) {
			dr.setErrorId(log_trace);
		} else {
			dr.setErrorId(UUID.randomUUID().toString());
		}
		return dr;
	}



	public static <T> DataResult<T> error(IError e) {
		int faultCode = e.getFaultCode();
		if (faultCode == 0) {
			faultCode = ResponseCode.RESULT_ERROR;
		}
		DataResult<T> dr = new DataResult<>(faultCode, e.getMessage());
		dr.setErrorId(e.getErrorId());
		dr.setCode(faultCode);
		return dr;
	}

	public static <T> DataResult<T> error(String msg, T data) {
		return new DataResult<T>(CommonFault.DEFAULT.getCode(), msg, data);
	}

	/**
	 * 错误的参数
	 *
	 * @param msg
	 * @return
	 */
	public static <T> DataResult<T> errorParam(String msg) {
		return new DataResult<T>(CommonFault.VALIDATE.getCode(), msg);
	}

	public static DataResult<?> error(Integer code, String msg) {
		return new DataResult<>(code, msg);
	}

	public static <T> DataResult<T> error(Integer code, String msg, T data) {
		return new DataResult<T>(code, msg, data);
	}

}
