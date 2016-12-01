package com.hszs.stb.model.auth;

import java.util.List;

public class Role {
	private int xh;
	private Integer id;
	private Integer roleid;
	private String name;
	private boolean enable;
	private List<Authority> authorities;

	public void setAuthorities(List<Authority> authorities){
		this.authorities=authorities;
	}
	
	public List<Authority> getAuthorities(){
		return this.authorities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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
	
}
