package com.season.voice.dto;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Bruce
 * @Date: 2021/6/10 16:03
 * @Description: 统一返回封装工具类
 */

@Component
public class DataResult<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5063776667672736098L;
    private String message;
    private T data;
    private String beginTime;
    private String endTime;
    private Long execTime;
    private String guid;
    private Map<String, Object> extension = new HashMap<>();

    private static String version;
    private int code;
    private String rqtid = "";
    private String errorId;
    private String result = "200";

    @Resource
    private Environment environment;

    @PostConstruct
    public void initVersion(){
        try {
            version = environment.getProperty("spring.cloud.nacos.discovery.metadata.version");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try{
            // METADATA_VERSION
            if(RequestContextHolder.getRequestAttributes() != null) {
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
                request = servletRequestAttributes.getRequest();
            }
        }catch (Exception e) {

        }
        return request;
    }

    private void init(){
        rqtid = new GetNewRid().getRid();
    }
    public DataResult() {
        super();
        init();
    }

    public DataResult(Integer code, String message) {
        super();
        init();
        this.message = message + "(" + rqtid + ")";
        this.code = code;

    }

    public DataResult(String message) {
        super();
        init();
        this.message = message + "(" + rqtid + ")";

    }

    public DataResult(Integer code, String message, T data) {
        super();
        init();
        this.message = message + "(" + rqtid + ")";
        this.data = data;
        this.code = code;
    }

    public DataResult(String message, T data) {
        super();
        init();
        this.message = message + "(" + rqtid + ")";
        this.data = data;

    }


//    @JsonIgnore
    public boolean isSuccess() {
        return code == 0;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getRqtid() {
        return rqtid;
    }

    public void setRqtid(String rqtid) {
        this.rqtid = rqtid;
    }

    public String getResult() {
        //fixme 确认999错误码含义并移除
        return this.code == 999 ? "999" : result;
//        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode(){
        return code;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

    public String getVersion() {
        return version;
    }

    public Map<String, Object> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, Object> extension) {
        this.extension = extension;
    }

    public void putExtension(String key, String value){
        if(extension == null){
            extension = new HashMap<>();
        }
        extension.put(key, value);
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", execTime=" + execTime +
                ", guid='" + guid + '\'' +
                ", version='" + version + '\'' +
                ", code=" + code +
                ", rqtid='" + rqtid + '\'' +
                '}';
    }
}

