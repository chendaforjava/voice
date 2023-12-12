package com.season.voice.exception.handler;

import com.season.voice.dto.DataResult;
import com.season.voice.dto.R;
import com.season.voice.dto.ResponseCode;
import com.season.voice.exception.*;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * 同意故障处理
 *
 * @author HuangHuaSheng
 * @date 2019年5月29日
 *
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ClientAbortException.class)
	@ResponseBody
	public void clientAbortExceptionHandler(Exception e) {
	}

	/**
	 * 权限异常
	 */
	@ExceptionHandler(PreAuthorizeException.class)
	public DataResult<?> preAuthorizeException(HttpServletRequest request, PreAuthorizeException ex)
	{
		return R.error("没有权限，请联系管理员授权");
	}


	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public DataResult<?> httpRequestMethodNotSupportedHandler(Exception e) throws Exception {
		logger.error("请求方式错误:{}", e.getMessage());
		return R.error("请求方式错误:" + e.getMessage());
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public DataResult<?> httpMessageNotReadableHandler(Exception e) throws Exception {
		logger.error("参数出错:{}", e.getMessage());
		return R.error("参数错误:"+e.getMessage());
	}

	/**
	 * 通用Exception异常捕获
	 * @param ex 自定义Exception异常类型
	 * @return Result
	 */
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public DataResult<?> handleException(HttpServletRequest request,Exception ex) {
		return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),  ex.getMessage());
	}

	/**
	 * 通用Exception异常捕获
	 * @param ex 自定义Exception异常类型
	 * @return Result
	 */
	@ExceptionHandler(value = HeathCheckException.class)
	@ResponseBody
	public DataResult<?> handleHeathCheckException(HttpServletRequest request,Exception ex) throws Exception {
		throw ex;
	}


	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public DataResult<?> exceptionErrorHandler(Exception e,HttpServletRequest request) throws Exception {
		if(e instanceof BaseException) {
            DefaultException exception  = (DefaultException)e;
            logger.info("业务异常错误code----1111>>>>>>{}",exception.getCode());
            logger.info("异常消息内容-->>>{}",exception.getMessage());
			ErrorInfo ei = null;
			if(null != exception.getCode()) {
				ei = ErrorInfo.convert(exception, request.getParameterMap());
			}else{
				ei = ErrorInfo.convert(e, request.getParameterMap());
			}
			logger.info("业务异常:{}",ei.toString());
			logger.info("异常:",e);
			DataResult<Object> error = R.error(ei);
			StringWriter errorsWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(errorsWriter));
			return error;
		}else if(e instanceof MissingServletRequestParameterException) {
			logger.error("缺少参数:{}", e.getMessage());
			DataResult<Object> dataResult = new DataResult<>(ResponseCode.RESULT_ERROR, "缺少参数");
			return dataResult;
		}else {
			logger.error("系统异常:{}", e.getMessage(),e);
			DataResult<Object> dataResult = new DataResult<>(ResponseCode.RESULT_ERROR, e.getMessage());
			return dataResult;
		}
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public DataResult<?> handler(HttpServletRequest request, ConstraintViolationException ex) {
		{
			logger.error("参数校验异常:{}", ex.getMessage());
			StringBuffer sb = new StringBuffer();
			for (ConstraintViolation violation : ex.getConstraintViolations()) {
				sb.append(violation.getMessage());

			}
			DataResult<Object> error = R.error(sb.toString());
			return error;
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public DataResult<?> handlerArgsValidate(HttpServletRequest request, MethodArgumentNotValidException ex) {
		{
			logger.error("参数错误:{}",ex.getMessage());
			StringBuffer sb = new StringBuffer();
			for (FieldError f : ex.getBindingResult().getFieldErrors()) {
				// sb.append(f.getField());
				sb.append(f.getDefaultMessage());
				sb.append(";");
			}
			return R.error(sb.toString());
		}
	}

	/**
	 * 捕捉 参数验证绑定异常
	 *
	 * @param request
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DataResult<?> handleBindException(HttpServletRequest request, HttpServletResponse response,
											 BindException ex) {
		logger.error("参数错误:{}",ex.getMessage());
		BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) ex.getBindingResult();
		String contentType = request.getContentType();
		StringBuilder builder = new StringBuilder("参数错误:");
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError objectError : fieldErrors) {
//			builder.append(objectError.getField());
			builder.append(objectError.getDefaultMessage());
			builder.append(";");
		}
		List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
		for (ObjectError objectError : globalErrors) {
			builder.append(objectError.getDefaultMessage());
			builder.append(";");
		}
		DataResult br = R.error(ResponseCode.RESULT_PARAMETER_ERROR, builder.toString());
		return br;
	}
}
