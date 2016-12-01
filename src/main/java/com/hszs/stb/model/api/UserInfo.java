package com.hszs.stb.model.api;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = 3852584702852366556L;
	
	public int uid;
	public String account;
	public String name;
	public int teamid;
	public String teamname;
	public String portrait;
	
	@JsonIgnore public Integer authcode;
	@JsonIgnore public String passwd;
	
	@JsonIgnore public Byte apptype;
	@JsonIgnore public String token;
}
