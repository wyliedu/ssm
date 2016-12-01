package com.hszs.stb.common.helper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public final class JSONUtil {

	private final static Log log = LogFactory.getLog(JSONUtil.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String ,Object> decodeJsonToMap(String json) {
		if(org.apache.commons.lang.StringUtils.isEmpty(json)){
			return null;
		}
		//不是json
		json = StringUtils.trim(json);
		if(!StringUtils.startsWith(json, "{") && !StringUtils.startsWith(json, "[")) {
			log.error("不是json： " + json);
			return null;
		}
		try {
			return objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (Exception e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}
	
	public static void main(String[] args){
		Object map = decodeJsonToMap("{\"code\":\"A00000\",\"data\":{\"iconurl\":\"http:\\/\\/img2.qiyipic.com\\/farm\\/icon\\/l\\/20130313\\/x6\\/bc\\/20130313x6bczfyse5fc30f8b10c8e80_9085895804.jpeg\"}}");
		System.out.println(map);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> decodeJsonToList(String json) {
		try {
			return objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (Exception e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}
	
	/**
	 * Decode a json string to T object
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T decodeJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (JsonMappingException e) {
			log.error("解析JSON错误：" + json, e);
		} catch (Exception e) {
			log.error("解析JSON错误：" + json, e);
		}
		return null;
	}
	
	public static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public static String jsonEncode(Object obj) {
		try{
			return objectMapper.writeValueAsString(obj);
		}catch(IOException ex){
			log.error("生成JSON错误：" + obj.getClass(), ex);
		}
		return null;
	}
	
	private static final ObjectMapper allObjectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public static String jsonEncodeAllFields(Object obj) {
		try{
			return allObjectMapper.writeValueAsString(obj);
		}catch(IOException ex){
			log.error("生成JSON错误：" + obj.getClass(), ex);
		}
		return null;
	}
}
