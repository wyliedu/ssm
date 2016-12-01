/*******************************************************
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * 
 * This file is part of Passport.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * 
 * Author(s): Feifan Yin <yinfeifan@qiyi.com>
 * 
 *******************************************************/


package com.hszs.stb.common.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpClientManager {

	private MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	private HttpClient httpClient = new HttpClient(httpConnectionManager);
	
	public HttpClientManager() {
		httpConnectionManager.getParams().setDefaultMaxConnectionsPerHost(8);
		httpConnectionManager.getParams().setMaxTotalConnections(16);
		httpConnectionManager.getParams().setConnectionTimeout(1000);
		httpConnectionManager.getParams().setSoTimeout(1000);
		httpConnectionManager.getParams().setTcpNoDelay(true);
		httpConnectionManager.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
	}
	
	public HttpClient getHttpClient(){
		return httpClient;
	}
	
	public HttpClientManager defaultMaxConnectionsPerHost(int defaultMaxConnectionsPerHost){
		httpConnectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnectionsPerHost);
		return this;
	}
	
	public HttpClientManager maxTotalConnections(int maxTotalConnections){
		httpConnectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		return this;
	}
	
	public HttpClientManager connectionTimeout(int connectionTimeout){
		httpConnectionManager.getParams().setConnectionTimeout(connectionTimeout);
		return this;
	}
	
	public HttpClientManager soTimeout(int soTimeout){
		httpConnectionManager.getParams().setSoTimeout(soTimeout);
		return this;
	}
	
	public HttpClientManager tcpNoDelay(boolean tcpNoDelay){
		httpConnectionManager.getParams().setTcpNoDelay(tcpNoDelay);
		return this;
	}
	
	public HttpClientManager retryHandler(HttpMethodRetryHandler retryHandler){
		httpConnectionManager.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
		return this;
	}
}
