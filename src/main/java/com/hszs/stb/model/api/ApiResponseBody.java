package com.hszs.stb.model.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.helper.JSONUtil;


@JsonIgnoreProperties(ignoreUnknown=true)
public final class ApiResponseBody implements Serializable{
	
	private static final long serialVersionUID = -2454950452642169245L;
	
	public static final ArrayList<Object> EMPTY_LIST = new ArrayList<Object>(1);
	
	private String code;
	private Object data;
	private String msg;
	
	private ApiResponseBody(){
	}
	
	
	private ApiResponseBody(String code, String msg, Object data){
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}

	public static final ApiResponseBody create(String code, String error, Object data){
		return new ApiResponseBody(code, error, data);
	}
	
	public static final ApiResponseBody createErrorBody(String code, String error){
		return new ApiResponseBody(code, error, null);
	}
	
	public static final ApiResponseBody createSuccessBody(Object data){
		return new ApiResponseBody(ApiCode.SUCCESS, null, data);
	}
	
	@SuppressWarnings("unchecked")
	public ApiResponseBody data(String key, Object value){
		
		if(data == null || data == EMPTY_LIST){
			data =new HashMap<String, Object>();
		}
		else if(!(data instanceof Map)){
			throw new UnsupportedOperationException("data已经赋值且部位Map类型：" + data);
		}
		HashMap<String, Object> map = (HashMap<String, Object>)data;
		map.put(key, value);
		return this;
	}
	
	@Override
	public String toString(){
		return JSONUtil.jsonEncode(this);
	}
}
