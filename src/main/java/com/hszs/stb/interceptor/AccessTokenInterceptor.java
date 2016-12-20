package com.hszs.stb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.ServiceContext;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.model.api.AccessToken;
import com.hszs.stb.model.api.UserInfo;


public class AccessTokenInterceptor extends HandlerInterceptorAdapter {
	
	
	static final Logger logger=LoggerFactory.getLogger(AccessTokenInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String tokenstr = request.getParameter("atoken");
		AccessToken atoken = AccessToken.decode(tokenstr);
		if(atoken == null) {
			final String requestURI = request.getRequestURI();
			logger.info(requestURI);
			throw new ServiceException(ApiCode.ERR_WRONG_ATOKEN, "缺少atoken参数或者atoken不正确");
		}
		final String deviceId = request.getParameter("deviceid");
		if(StringUtils.isNotEmpty(deviceId)) {
			ServiceContext.setCurrentDeviceId(deviceId);
			if(AccessToken.crc32(deviceId) != atoken.device_checksum) {
				throw new ServiceException(ApiCode.ERR_WRONG_ATOKEN, "atoken已经失效（设备不匹配）");
			}
		}
		final UserInfo info = new UserInfo();
		info.uid = atoken.uid;
		info.apptype = atoken.apptype;
		info.token = tokenstr;
		ServiceContext.setCurrentAccount(info);
		return super.preHandle(request, response, handler);
	}

}
