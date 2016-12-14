package com.hszs.stb.model.auth;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author du
 *
 */
public class Account {

	private Integer accountid;
	@Email(message="{email.not.correct}")
	private String email;
	private String name;
	@NotEmpty(message="{username.not.empty}")
	private String username;
	private String password;
	private Integer roleid;
	private boolean enable;
	private int xh;
	private String rolename;
	@Pattern(regexp = "1[3-8]\\d{9}", message = "{payphone.not.legal}")
	private String payphone;
	
	public void setEmail(String email){
		this.email=email;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getEmail(){
		return this.email;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPayphone() {
		return payphone;
	}
	public void setPayphone(String payphone) {
		this.payphone = payphone;
	}
}
