package com.hszs.stb.common.exception;


public class ServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private int apiCode = 0; 
	private String message;
	
	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	
	public ServiceException(int code, String message) {
		super(message);
		this.apiCode = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}
	
	public int getOpenAPICode() {
		return apiCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
