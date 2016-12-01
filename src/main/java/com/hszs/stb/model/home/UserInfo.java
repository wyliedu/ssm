package com.hszs.stb.model.home;

/**
 * 用户信息
 * @author Administrator
 *
 */
public class UserInfo {

	private int id;					//用户ID
	private String account;			//用户账号
	private String name;			//用户姓名
	private int usertype;			//用户类型
	private boolean enable;			//用户是否可用
	private String error;			//用户登录失败原因
	private int communityId;		//所属社区id
	private int communityName;		//社区名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getCommunityName() {
		return communityName;
	}
	public void setCommunityName(int communityName) {
		this.communityName = communityName;
	}

	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	
}
