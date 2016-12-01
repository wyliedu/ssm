package com.hszs.stb.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hszs.stb.common.helper.RequestHelper;
import com.hszs.stb.model.api.UserInfo;


public final class ServiceContext {
	
	static final Logger logger=LoggerFactory.getLogger(ServiceContext.class);
	
	private static final ThreadLocal<ContextObject> CONTEXT = new ThreadLocal<ContextObject>();
	
	private static ContextObject context(boolean create) {
		ContextObject co = CONTEXT.get();
		if(co == null) {
			if(create) {
				co = new ContextObject();
				CONTEXT.set(co);
				return co;
			}
			return null;
		}
		return co;
	}
	
	public static void clean() {
		CONTEXT.set(null);
	}
	
	public static String getRequestIp() {
		HttpServletRequest request = context(false).REQUEST;
		if(request == null) {
			return null;
		}
		return RequestHelper.getRemoteAddr(request);
	}
	
	public static void setHttpServletRequest(HttpServletRequest request) {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			ctx.REQUEST = request;
			ctx.ts = System.currentTimeMillis();
		}
	}
	
	public static String getCurrentDeviceId() {
		final ContextObject ctx = context(false);
		return ctx == null ? null : ctx.DEVICE_ID;
	}
	
	public static void setCurrentDeviceId(String deviceId) {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			ctx.DEVICE_ID = deviceId;
		}
	}
	
	public static void setRequestTimestamp(long ts) {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			ctx.ts = ts;
		}
	}
	
	public static long getRequestTimestamp() {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			return ctx.ts;
		}
		return 0L;
	}
	
	public static void setCurrentAccount(UserInfo info) {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			ctx.ACCOUNT = info;
		}
	}
	
	public static UserInfo currentAccount() {
		final ContextObject ctx = context(false);
		return ctx == null ? null : ctx.ACCOUNT;
	}
	
	public static int currentUid() {
		final ContextObject ctx = context(false);
		if(ctx == null) {
			return 0;
		}
		UserInfo view = ctx.ACCOUNT;
		return view == null ? 0 : view.uid;
	}
	
	public static byte currentApptype() {
		final ContextObject ctx = context(false);
		if(ctx == null) {
			return 0;
		}
		UserInfo view = ctx.ACCOUNT;
		return view == null ? 0 : view.apptype;
	}

	
	public static void setErrorCode(String errorCode) {
		final ContextObject ctx = context(false);
		if(ctx != null) {
			ctx.ERROR_CODE = errorCode;
		}
	}
	
	public static void setErrorMessage(String errorMsg) {
		final ContextObject ctx = context(true);
		if(ctx != null) {
			ctx.ERROR_MSG = errorMsg;
		}
	}
	
	public static String getErrorCode() {
		final ContextObject ctx = context(false);
		
		return ctx == null ? null : ctx.ERROR_CODE;
	}
	
	public static String getErrorMessage() {
		final ContextObject ctx = context(false);
		return ctx == null ? null : ctx.ERROR_MSG;
	}
	
	public static Object[] logObjects() {
		final ContextObject ctx = context(false);
		if(ctx == null) {
			return null;
		}
		final long cost = Math.min(System.currentTimeMillis() - ctx.ts, 99999L);
		return new Object[]{
				ctx.REQUEST == null 	? null : RequestHelper.getRemoteAddr(ctx.REQUEST),
				ctx.REQUEST == null 	? null : ctx.REQUEST.getRequestURI(),
				ctx.ERROR_CODE == null 	? ApiCode.SUCCESS : ctx.ERROR_CODE,
				ctx.DEVICE_ID,
				ctx.ACCOUNT == null 	? null : ctx.ACCOUNT.uid,
				cost
		};
	}
	
	static class ContextObject {
		public UserInfo ACCOUNT;
		public String ERROR_CODE;
		public String ERROR_MSG;
		public String DEVICE_ID;
		public HttpServletRequest REQUEST;
		public long ts;
	}
}
