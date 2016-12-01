package com.hszs.stb.web.home;

import javax.servlet.http.HttpServletRequest;

import com.hszs.stb.model.auth.AccountAuth;
import com.hszs.stb.model.auth.PermissionMenu;


public class AuthHelper {
	
	public static void setSessionAccountAuth(HttpServletRequest request, AccountAuth accountAuth){
		request.getSession().setAttribute("accountAuth", accountAuth);
	}
	
	public static AccountAuth getSessionAccountAuth(HttpServletRequest request){
		return (AccountAuth)request.getSession().getAttribute("accountAuth");
	}
	
	public static void setRequestPermissionMenu(HttpServletRequest request, PermissionMenu permissionMenu){
		request.setAttribute("permissionMenu", permissionMenu);
	}
	
	public static PermissionMenu getRequestPermissionMenu(HttpServletRequest request){
		return (PermissionMenu)request.getAttribute("permissionMenu");
	}
	
}
