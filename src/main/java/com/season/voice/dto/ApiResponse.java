package com.season.voice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: MiKey
 * @Date: 2022/3/23 17:31
 * @Description: 用于服务间通宵结果
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> implements Serializable {

    private T data;

    private Boolean isOk = Boolean.FALSE;

    private String message;

    public static ApiResponse ok(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setIsOk(Boolean.TRUE);
        apiResponse.setMessage("成功");
        return apiResponse;
    }

    public static ApiResponse ok() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(true);
        apiResponse.setIsOk(Boolean.TRUE);
        apiResponse.setMessage("成功");
        return apiResponse;
    }

    public static ApiResponse isOk(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setIsOk(Boolean.TRUE);
        apiResponse.setMessage("成功");
        return apiResponse;
    }

    public static ApiResponse isOk() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(null);
        apiResponse.setIsOk(Boolean.TRUE);
        apiResponse.setMessage("成功");
        return apiResponse;
    }

    public static ApiResponse fail() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(null);
        apiResponse.setIsOk(Boolean.FALSE);
        apiResponse.setMessage("失败");
        return apiResponse;
    }

    public static ApiResponse fail(String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(null);
        apiResponse.setIsOk(Boolean.FALSE);
        apiResponse.setMessage(message);
        return apiResponse;
    }


}
