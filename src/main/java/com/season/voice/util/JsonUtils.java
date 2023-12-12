package com.season.voice.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class JsonUtils {
	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
	private static ObjectMapper mapper = new ObjectMapper() {
		{
//			setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//			getSerializationConfig().setDateFormat("yyyy-MM-dd HH:mm:ss");
			configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

			// 设置全局的时间转化
			SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			setDateFormat(smt);
			setTimeZone(TimeZone.getTimeZone("GMT+8"));// 解决时区差8小时问题

			// 设置中文编码格式
//	        List<MediaType> list = new ArrayList<MediaType>();
//	        list.add(MediaType.APPLICATION_JSON_UTF8);
//	        jackson2HttpMessageConverter.setSupportedMediaTypes(list);

			// 生成json时，将所有Long转换成String
//	        SimpleModule simpleModule = new SimpleModule();
//	        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//	        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//	        objectMapper.registerModule(simpleModule);
		}
	};
	private static final String SUCCESS = "{\"code\":\"success\",\"success\":true}";
	private static final String ERROR_CODE = "{\"code\":\"error\",\"error\":true}";

	private static SerializationConfig getJsonConfig() {
		return mapper.getSerializationConfig();
	}

	public static String toString(Object obj) {
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

//		String result=JSONSerializer.toJSON(obj,getJsonConfig()).toString(0);
		return result;
	}

	public static <T extends Object> T json2Object(String jsonString, Class<T> clazz) {
		if (jsonString == null) {
			return null;
		}
		try {
//			 mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
//			 mapper.getDeserializationConfig().withHandler(hs);
//			 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
//			 mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			T t = mapper.readValue(jsonString, clazz);
			return t;
		} catch (Exception e) {
			log.error("Json转对象出错:info:[{}] , error message : {}", jsonString, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * json字符串转换list
	 * 
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T extends Object> List<T> json2List(String jsonStr, Class<T> cls) {

		try {
			JavaType javaType = getCollectionType(ArrayList.class, cls);
			List<T> list = mapper.readValue(jsonStr, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 读取Json属性的全路径名
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
//	public static List<String> readPropertyName(String json) throws Exception {
//		HashMap map = json2Object(json, HashMap.class);
//		mapper.getNodeFactory().rawValueNode(json);
//	}
}
