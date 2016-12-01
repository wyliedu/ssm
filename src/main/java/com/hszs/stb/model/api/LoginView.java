package com.hszs.stb.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

public class LoginView extends AbstractJsonModel{

	private static final long serialVersionUID = -8387838621675702764L;
	
	public UserInfo userinfo;

	@JsonInclude(value=Include.NON_NULL)
	public String atoken;

	
	//public LbsConfig lbsConfig;
	
	//public UserVerifyInfo verifyInfo;
	
	
	public static LoginView of(UserInfo userinfo) {
		return of(userinfo, null);
	}
	
	public static LoginView of(UserInfo userinfo, String atoken) {
		LoginView view = new LoginView();
		view.userinfo = userinfo;
		view.atoken = atoken;
		return view;
	}

}
