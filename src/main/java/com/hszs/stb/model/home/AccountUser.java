package com.hszs.stb.model.home;

public class AccountUser {
	
	private String userAccount;   //登录帐号
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
