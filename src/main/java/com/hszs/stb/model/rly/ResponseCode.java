package com.hszs.stb.model.rly;

public enum ResponseCode {

	SUCC("000000", "调用成功"),
	DUP_SUBACCOUNT("111150", "【账号】子账户名称重复"),
	SUCC2("000000", "调用成功"),
	SUCC3("000000", "调用成功"),
	SUCC4("000000", "调用成功"),
	SUCC5("000000", "调用成功");
	
	public final String code;
	public final String description;
	
	ResponseCode(String code, String description){
		this.code = code;
		this.description = description;
	}
	

	
	//{"statusMsg":"【账号】子账户名称重复","statusCode":"111150"}
}
