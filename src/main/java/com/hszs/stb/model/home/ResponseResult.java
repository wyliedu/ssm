package com.hszs.stb.model.home;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.model.api.ApiResponseBody;

/**
 * 接口返回pojo
 * @author du
 *
 */
public class ResponseResult {

	private int code=0;			  //结果编码 0为成功其余为失败
	public static final int SUCCESS = 0;//成功
	private String message ;      //错误信息
	private Object data;		  		//结果
	
	public ResponseResult(){
	}
	
	public ResponseResult(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	public ResponseResult(int code,String message, Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public static final ResponseResult create(int code, String error, Object data){
		return new ResponseResult(code, error, data);
	}
	
	public static final ResponseResult createErrorBody(int code, String error){
		return new ResponseResult(code, error, null);
	}
	
	public static final ResponseResult createSuccessBody(Object data){
		return new ResponseResult(ResponseResult.SUCCESS, null, data);
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
