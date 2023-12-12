package com.season.voice.error;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.season.voice.constant.CommonFault;
import com.season.voice.exception.BaseException;
import com.season.voice.exception.DefaultException;
import com.season.voice.util.ApiCode;
import com.season.voice.util.JsonUtils;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.UUID;


/*
import brave.Tracing;*/

/**
 * 错误类
 *
 * @author HuangHuaSheng
 * @date 2019年8月12日
 *
 */
public class ErrorInfo implements IError {

	/**
	 * 接口编码
	 */
	private int apiCode;
	/**
	 * 故障编码
	 */
	private int faultCode;

	/**
	 * 错误ID
	 */
	private String errorId;
	/**
	 * 输入参数
	 */
	private String args;
	/**
	 * 错误提示
	 */
	private String message;

	/**
	 *
	 * @param moduleCode 模块编号
	 * @param funcNum    功能编号
	 * @param ex         异常
	 * @param args       输入参数
	 * @return
	 */
	public static ErrorInfo convert(int moduleCode, int funcNum, Throwable ex, Object... args) {
		int faultCode = handlefaultCode(ex);
		return ErrorInfo.convert(moduleCode, funcNum, faultCode, ex, args);
	}

	public static ErrorInfo convert(int moduleCode, int funcNum, String message, Throwable ex, Object... args) {
		int faultCode = handlefaultCode(ex);
		return ErrorInfo.convert(moduleCode, funcNum, faultCode, message, ex, args);
	}

	/**
	 *
	 * @param moduleCode
	 * @param funcNum
	 * @param faultCode  故障号
	 * @param ex
	 * @param args
	 * @return
	 */
	public static ErrorInfo convert(int moduleCode, int funcNum, int faultCode, Throwable ex, Object... args) {
		return ErrorInfo.convert(moduleCode, funcNum, faultCode, null, ex, args);
	}

	public static ErrorInfo convert(int moduleCode, int funcNum, int faultCode, String message, Throwable ex,
			Object... args) {

		int apiCode = ApiCode.getCode(moduleCode, funcNum);
		faultCode = apiCode * 100 + faultCode;
		ErrorInfo e = new ErrorInfo();
		e.faultCode = faultCode;
		e.apiCode = apiCode;
		String log_trace = null;
		if (log_trace != null) {
			e.errorId = log_trace;
		} else {
			e.errorId = UUID.randomUUID().toString();
		}
		if (args != null) {
			e.args = JsonUtils.toString(args);
		}
		if (StringUtils.isNotEmpty(message)) {
			e.message = message;
		} else if (ex != null) {
			if (ex instanceof NullPointerException) {
				e.message = CommonFault.NOT_EXISTS.getMessage();
			} else if (ex instanceof ConstraintViolationException) {
				ConstraintViolationException ce = (ConstraintViolationException) ex;
				StringBuffer sb = new StringBuffer();
				for (ConstraintViolation violation : ce.getConstraintViolations()) {
					sb.append(violation.getMessage());
					sb.append(";");
				}
				e.message = CommonFault.VALIDATE.getMessage() + ":" + sb;
			} else if (ex instanceof MethodArgumentNotValidException) {
				MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
				StringBuffer sb = new StringBuffer();
				for (FieldError f : me.getBindingResult().getFieldErrors()) {
					sb.append(f.getDefaultMessage());
					sb.append(";");
				}
				e.message = CommonFault.VALIDATE.getMessage() + ":" + sb;
			} else if (ex instanceof TransactionTimedOutException) {
				e.message = CommonFault.TIMEOUT.getMessage();
			} else if (ex instanceof ResourceAccessException) {
				e.message = CommonFault.BREAKER.getMessage() + ":" + ex.getMessage();
			} else {
				e.message = ex.getMessage();
			}
		}
		return e;
	}

	/**
	 * 处理故障编号
	 *
	 * @param ex
	 * @return
	 */
	private static int handlefaultCode(Throwable ex) {
		if (ex == null) {
			return CommonFault.DEFAULT.getCode();
		}
		if (ex instanceof BaseException) {
			return ((BaseException) ex).getFaultCode();
		} else if (ex instanceof ConstraintViolationException) {
			return CommonFault.VALIDATE.getCode();
		} else if (ex instanceof NullPointerException) {
			return CommonFault.NOT_EXISTS.getCode();
//		} else if (ex instanceof com.netflix.client.ClientException) {
//			return CommonFault.BREAKER.getCode();
		} else if (ex instanceof TransactionTimedOutException) {
			return CommonFault.TIMEOUT.getCode();
		} else if (ex instanceof ResourceAccessException) {
			return CommonFault.BREAKER.getCode();
		} else {
			return CommonFault.DEFAULT.getCode();
		}
	}

	/**
	 *
	 * @param ex
	 * @param args
	 * @return
	 */
	public static ErrorInfo convert(Throwable ex, Object... args) {
		ErrorInfo e = new ErrorInfo();
		e.errorId = UUID.randomUUID().toString();
		String log_trace = null;
		/*try {
			Tracing tracing = SpringContextUtils.getBean(Tracing.class);
			log_trace = tracing.tracer().currentSpan().context().spanIdString();
		} catch (Exception ex2) {
		}*/
		if (log_trace != null) {
			e.errorId = log_trace;
		} else {
			e.errorId = UUID.randomUUID().toString();
		}
		if (args != null) {
			e.args = JsonUtils.toString(args);
		}
		if (ex != null) {
			e.message = ex.getMessage();
		}
		return e;
	}

	/**
	 *
	 * @param ex
	 * @param args
	 * @return
	 */
	public static ErrorInfo convert(DefaultException ex, Object... args) {
		ErrorInfo e = new ErrorInfo();
		e.errorId = UUID.randomUUID().toString();
		String log_trace = null;
		if (log_trace != null) {
			e.errorId = log_trace;
		} else {
			e.errorId = UUID.randomUUID().toString();
		}
		if (args != null) {
			e.args = JsonUtils.toString(args);
		}
		if (ex != null) {
			e.message = ex.getMessage();
			if(ex instanceof BaseException) {
				DefaultException exception  = ex;
				if(null != exception && null != exception.getCode()) {
					e.faultCode = exception.getCode();
				}
			}
		}
		return e;
	}

	@Override
	public int getApiCode() {
		return apiCode;
	}

	@Override
	public String getErrorId() {
		return errorId;
	}

	@Override
	public String getArgs() {
		return args;
	}

	@Override
	public int getFaultCode() {
		return faultCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return JsonUtils.toString(this);
	}
}
