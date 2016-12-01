package com.hszs.stb.web.home;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hszs.stb.common.exception.PermissionException;
import com.hszs.stb.model.auth.AccountAuth;
import com.hszs.stb.model.auth.PermissionMenu;
import com.hszs.stb.model.home.AuthPassport;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
        	AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
       	 
        	//没有声明需要权限,或者声明不验证权限
       	 	if(authPassport == null || authPassport.validate() == false)
                return true;
            else{
            	AccountAuth accountAuth=AuthHelper.getSessionAccountAuth(request);
            	if(accountAuth!=null)
            	{
            		boolean hasPermission=false;
            		String requestServletPath=request.getServletPath();
            	
            		for(PermissionMenu permissionMenu : accountAuth.getAccountRole().getPermissionMenus()){
            			//将给定的正则表达式编译并赋予给Pattern类 
            			Pattern pattern = Pattern.compile(permissionMenu.getPermission(),Pattern.CASE_INSENSITIVE);
            			Matcher matcher = pattern.matcher(requestServletPath);   //生成一个给定命名的Matcher对象 
            			if(matcher.find()){
            				hasPermission=true;
            				AuthHelper.setRequestPermissionMenu(request, permissionMenu);
            			}
            		}
            		
            		return true;
/*            		if(hasPermission)
            			return true;
            		else
            			throw new PermissionException("没有权限！");*/
            		
            	}
            	else
            	{
            		StringBuilder urlBuilder=new StringBuilder(request.getContextPath());
            		urlBuilder.append("/home/login");
            		if(request.getServletPath()!=null && !request.getServletPath().isEmpty()){
            			urlBuilder.append("?returnUrl=");
            			
            			StringBuilder pathAndQuery=new StringBuilder(request.getServletPath());
            			if(request.getQueryString()!=null && !request.getQueryString().isEmpty()){
                			pathAndQuery.append("?");
                			pathAndQuery.append(request.getQueryString());
                		}
            			
            			urlBuilder.append(URLEncoder.encode(pathAndQuery.toString(), "UTF-8"));
            		}
            		
            		response.sendRedirect(urlBuilder.toString());
            		return false;
            	}
            }
        }else{
       	 	return true;   
        }
	 }
}