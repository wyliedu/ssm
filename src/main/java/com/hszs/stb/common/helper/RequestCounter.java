package com.hszs.stb.common.helper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.hszs.stb.common.util.ExecutorPools;

public class RequestCounter {
	private static final Logger logger = LoggerFactory.getLogger("crontabLog");
	public static final RequestCounter instance = new RequestCounter();
	private static final String OTHER_REQUEST = "OTHER_REQUEST";
	public static final String REQUEST_SET_KEY = "REQUEST_SET_KEY";
	public static final int MAX_QPS_ITEMS = 100 * 100;
	
	public static final int[] CODE_SET = {200, 499, 500, 501, 502, 503, 301, 302, 600};
	private boolean qpsMapIsFull = false;
	
	private Set<String> cachedApiSet = null;
	private ConcurrentHashMap<String, RequestInfo> qpsmap = new ConcurrentHashMap<String, RequestInfo>(256);
	private static final Set<String> DEFAULT_REQUEST_SET = Sets.newHashSet("https://openapi.baidu.com/oauth/2.0/authorize"
			, "https://openapi.baidu.com/oauth/2.0/token"
			, "https://openapi.baidu.com/rest/2.0/passport/users/getInfo"
			, "https://openapi.baidu.com/rest/2.0/friends/getFriends"
			, "http://api.t.sina.com.cn/oauth/request_token"
			, "http://api.t.sina.com.cn/oauth/authorize"
			, "http://api.t.sina.com.cn/oauth/access_token"
			, "https://api.weibo.com/oauth2/get_oauth2_token"
			, "https://api.weibo.com/oauth2/authorize"
			, "https://api.weibo.com/oauth2/access_token"
			, "https://api.weibo.com/2/users/show.json"
			, "https://api.weibo.com/2/friendships/friends.json"
			, "https://graph.renren.com/oauth/authorize"
			, "https://graph.renren.com/oauth/token"
			, "http://graph.renren.com/renren_api/session_key"
			, "https://graph.qq.com/oauth2.0/authorize"
			, "https://graph.qq.com/oauth2.0/token"
			, "https://graph.z.qq.com/moc2/me"
			, "https://open.t.qq.com/cgi-bin/oauth2/authorize"
			, "https://open.t.qq.com/cgi-bin/oauth2/access_token"
			, "https://graph.qq.com/oauth2.0/me"
			
			, "https://mapi.alipay.com/gateway.do"
			, "https://www.alipay.com/cooperate/gateway.do"
			, "http://api.kaixin001.com/oauth2/authorize"
			, "https://api.kaixin001.com/oauth2/access_token"
			, "https://api.kaixin001.com/users/me.json"
			, "https://api.kaixin001.com/friends/me.json"
			, "https://passport.lenovo.com/wauthen/login"
			, "https://i2.feixin.10086.cn/api/user.json"
			, OTHER_REQUEST);
	
	private RequestCounter(){}
	
	public static class RequestInfo {
		public AtomicInteger rt = new AtomicInteger();
		public AtomicInteger count = new AtomicInteger();
	}
	
	public static interface IRequestSum{
		void sum(String urlAndcode, int count, int rt);
	}
	
	public void count(final String url, final int code, final int rt){
		
		ExecutorPools.getInstance().requestCounterExecutor.submit(new Runnable(){
			public void run() {
				if(qpsMapIsFull){
					return;
				}
				
				String key = key(getPureUrl(url), code);
				RequestInfo info = qpsmap.get(key);
				if(info == null){
					info = new RequestInfo();
					qpsmap.put(key, info);
				}
				info.count.incrementAndGet();
				info.rt.addAndGet(rt);
			}
		});
	}
	
	public String getPureUrl(String url){
		String pureUrl = StringUtils.substringBefore(url, "?");
		final Set<String> apiSet = getApiSet();
		for(String u : apiSet){
			if(StringUtils.startsWith(pureUrl, u)){
				return u;
			}
		}
		return OTHER_REQUEST;
	}
	
	public String genMemKey(String prefix, String api, int code){
		return new StringBuilder(80).append(prefix).append(api).append(":").append(code).toString();
	}
	
	public void setApiSet(Set<String> newSet){
		if(newSet != null){
			newSet.addAll(DEFAULT_REQUEST_SET);
			cachedApiSet = newSet;
		}
	}
	
	public Set<String> getApiSet(){
		return cachedApiSet == null ? DEFAULT_REQUEST_SET : cachedApiSet;
	}
	
	public final int[] getCodeSet(){
		return CODE_SET;
	}
	
	public void sum(IRequestSum rs){
		for(Map.Entry<String, RequestInfo> en : qpsmap.entrySet()){
			RequestInfo info = en.getValue();
			int count = info.count.getAndSet(0);
			int rt = info.rt.getAndSet(0);
			rs.sum(en.getKey(), count, rt);
		}
		qpsMapIsFull = qpsmap.size() > MAX_QPS_ITEMS;
		if(qpsMapIsFull){
			logger.error("要统计的Request太多了{}", qpsmap.size());
			return;
		}
	}
	
	private String key(String url, int code){
		return new StringBuilder(url).append(":").append(code).toString();
	}
	
	public static class ReqCodeValue{
		public ReqCodeValue(){}
		public ReqCodeValue(String api, int code, int count, int rt){
			this.api = api;
			this.code = code;
			this.count = count;
			this.rt = rt;
		}
		public String api;
		public int code;
		public int count;
		public int rt;
	}
}
