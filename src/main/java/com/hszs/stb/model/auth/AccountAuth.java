package com.hszs.stb.model.auth;

public class AccountAuth {
	
	private Integer id;     //用户
	private String name;    //名字
	private String username;  //登陆用户名
	private AccountRole accountRole;  //角色信息
	
	public AccountAuth(Integer id, String name, String username){
		this.id=id;
		this.name=name;
		this.username=username;
	}
	
	public void setId(Integer id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setAccountRole(AccountRole accountRole){
		this.accountRole=accountRole;
	}
	
	public Integer getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getUsername(){
		return this.username;
	}
	public AccountRole getAccountRole(){
		return this.accountRole;
	}
	
}
