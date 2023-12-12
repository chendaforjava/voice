package com.season.voice.dto;
/**
 * 
 * 状态码枚举
 * 
 * @author xiejinei on 2018年12月12日
 *
 */
public class ResponseCode {
	/**
	 * 成功状态码
	 */
	public static final Integer RESULT_SUCCESS = 10200;
	
	/**
	 * 失败状态
	 */
	public static final Integer RESULT_FAIL = 10500;
	
	/**
	 * 无权状态码
	 */
	public static final Integer RESULT_UNAUTHORIZED = 10001;
	
	/**
	 * 没登录态码
	 */
	public static final Integer RESULT_NO_LOGIN = 10002;
	
	/**
	 * 业务操作失败状态码
	 */
	public static final Integer RESULT_OPERATION_FAIL = 10003;
	
	
	/**
	 * 不支持方法请求状态码
	 */
	public static final Integer RESULT_METHOD_NOT_SUPPORT = 10004;
	
	
	/**
	 * 参数不正确请求状态码
	 */
	public static final Integer RESULT_PARAMETER_ERROR = 10005;
	
	
	/**
	 * 服务器异常状态码
	 */
	public static final Integer RESULT_ERROR = 10006;
	/**
	 * 流程画的不规范，无法正常部署
	 */
	public static final Integer FLOW_ERROR = 50001;

	
	
	/**
	 * 用户名密码错误
	 */
	public static final Integer RESULT_USERNAME_PASSWORD = 10007;
	
	
	/**
	 * TOKEN无效状态码
	 */
	public static final Integer RESULT_TOKEN_INVALID = 10008;
	
	
	
	/**
	 * 请求无TOKEN
	 */
	public static final Integer RESULT_NO_TOKEN = 10009;

}
