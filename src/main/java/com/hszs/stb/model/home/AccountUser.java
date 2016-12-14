package com.hszs.stb.model.home;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

public class AccountUser {
/*	@Null  被注释的元素必须为 null  
	@NotNull   被注释的元素必须不为 null  
	@AssertTrue     被注释的元素必须为 true  
	@AssertFalse    被注释的元素必须为 false  
	@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
	@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
	@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
	@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
	@Size(max=, min=)   被注释的元素的大小必须在指定的范围内  
	@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内  
	@Past  被注释的元素必须是一个过去的日期  
	@Future    被注释的元素必须是一个将来的日期  
	@Pattern(regexp=,message=)  被注释的元素必须符合指定的正则表达式  
	Hibernate Validator 附加的constraint  
	@NotBlank(message =)   验证字符串非null，且长度必须大于0  
	@Email 被注释的元素必须是电子邮箱地址  
	@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内  
	@NotEmpty  被注释的字符串的必须非空  
	@Range(min=,max=,message=)  被注释的元素必须在合适的范围内*/
	@NotEmpty(message="{username.not.empty}")
	private String userAccount;   //登录帐号
	
	@NotEmpty(message="{password.not.empty}")
	private String userPassword;   //登录密码

	public String getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
