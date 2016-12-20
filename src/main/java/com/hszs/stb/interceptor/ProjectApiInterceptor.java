package com.hszs.stb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hszs.stb.common.ServiceContext;

public class ProjectApiInterceptor extends HandlerInterceptorAdapter {
	
	public static final Logger userVisistLogger = LoggerFactory.getLogger("visitLog");
	
	public static final String SESSION_COOKIE_KEY = "QC005";
	
	
	static final Logger logger=LoggerFactory.getLogger(ProjectApiInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		ServiceContext.clean();
		ServiceContext.setHttpServletRequest(request);
		return super.preHandle(request, response, handler);

	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ServiceContext.clean();
	}
	
}
