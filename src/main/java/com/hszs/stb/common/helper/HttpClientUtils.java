package com.hszs.stb.common.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public final class HttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
	
	public static final String USER_AGENT = "HttpClient/3.1/PP";
	public static final int UNKNOWN_HTTP_CODE = 600;
	
	public static Long getLastModified(String urlAsStr){
		int code = 200;
		long timestamp = System.currentTimeMillis();
		try{
			URL url = new URL(urlAsStr); 
			URLConnection urlc = url.openConnection();
			return urlc.getLastModified();
		}catch(Exception ex){
			log.error("Fail to get last-modified: {}", new Object[]{urlAsStr}, ex);
		}finally{
			RequestCounter.instance.count(urlAsStr, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final String httpGet(String url){
		HttpClient client = new HttpClient();
		return httpGet(url, client);
	}
	
	public static final String httpGet(String url, HttpClient client){
		GetMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			code = client.executeMethod(method);
			String msg = method.getResponseBodyAsString();
			return msg;
		} catch (HttpException e) {
			log.error("httpGet error with url: " + url);
		} catch (IOException e) {
			log.error("httpGet error with url: " + url);
		} finally{
			method.releaseConnection();
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final String httpGet(String url, Map<String, String> params){
		return httpGet(url, params, null);
	}
	
	public static final String httpGetNTimes(String url, Map<String, String> params, int times){
		return httpGetNTimes(url, params, null, times);
	}
	
	public static final String httpGetNTimes(String url, Map<String, String> params, Map<String, String> headers, int times){
		for(int i = 0; i<times; ++i){
			String response = httpGet(url, params, headers);
			if(response != null){
				return response;
			}
		}
		return null;
	}
	
	public static final String httpPostNTimes(String url, Map<String, String> params, int times){
		return httpPostNTimes(url, params, null, times);
	}
	
	public static final String httpPostNTimes(String url, Map<String, String> params, Map<String, String> headers, int times){
		for(int i = 0; i<times; ++i){
			String response = httpPost(url, params, headers);
			if(response != null){
				return response;
			}
		}
		return null;
	}
	
	public static final Map<String, Object> httpGetNTimesToMap(String url, Map<String, String> params, int times){
		return httpGetNTimesToMap(url, params, null, times);
	}
	
	public static final Map<String, Object> httpGetNTimesToMap(String url, Map<String, String> params, Map<String, String> headers, int times){
		String response = httpGetNTimes(url, params, headers, times);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Map<String ,Object> responseMap = JSONUtil.decodeJsonToMap(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final List<Object> httpGetNTimesToList(String url, Map<String, String> params, int times){
		return httpGetNTimesToList(url, params, null, times);
	}
	
	public static final List<Object> httpGetNTimesToList(String url, Map<String, String> params, Map<String, String> headers, int times){
		String response = httpGetNTimes(url, params, headers, times);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		List<Object> responseMap = JSONUtil.decodeJsonToList(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final Object httpGetNTimesToObject(String url, Map<String, String> params, int times){
		return httpGetNTimesToObject(url, params, null, times);
	}
	
	public static final Object httpGetNTimesToObject(String url, Map<String, String> params, Map<String, String> headers, int times){
		String response = httpGetNTimes(url, params, headers, times);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Object responseMap = JSONUtil.decodeJson(response, Object.class);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final String httpGet(String url, Map<String, String> params, Map<String, String> headers){
		HttpClient client = new HttpClient();
		String fullurl = buildGetUrl(url, params);
		log.info(fullurl);
		GetMethod method = new GetMethod(fullurl);
		if(headers != null){
			for (Map.Entry<String, String> en : headers.entrySet()) {
				method.addRequestHeader(en.getKey(), en.getValue());
			}
		}
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			code = client.executeMethod(method);
			if(isValidHttpCode(code)){
				return readFullResponseBody(method);
			}else{
				log.error("Http response code(" + code + ") is not 200 when get " + fullurl + " with parameters " + params);
			}
		} catch (HttpException e) {
			log.error("Unexpected HttpException when http get " + fullurl + " with parameters " + params, e);
		} catch (IOException e) {
			log.error("Unexpected IOException when http get " + fullurl + " with parameters " + params, e);
		} finally{
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final InputStream httpGetAsStream(String url){
		return httpGetAsStream(url, null);
	}
	
	public static final InputStream httpGetAsStream(String url, Map<String, String> params){
		HttpClient client = new HttpClient();
		String fullurl = buildGetUrl(url, params);
		GetMethod method = new GetMethod(fullurl);
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			code = client.executeMethod(method);
			if(isValidHttpCode(code)){
				return method.getResponseBodyAsStream();
			}else{
				log.error("Http response code(" + code + ") is not 200 when get " + fullurl + " with parameters " + params);
			}
		} catch (HttpException e) {
			log.error("Unexpected HttpException when http get " + fullurl + " with parameters " + params, e);
		} catch (IOException e) {
			log.error("Unexpected IOException when http get " + fullurl + " with parameters " + params, e);
		} finally{
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final Header[] httpHead(String url){
		return httpHead(url, null, null);
	}
	
	public static final Header[] httpHead(String url, Map<String, String> params){
		return httpHead(url, params, null);
	}
	
	public static final Header[] httpHead(String url, Map<String, String> params, Map<String, String> headers){
		HttpClient client = new HttpClient();
		String fullurl = buildGetUrl(url, params);
		HeadMethod method = new HeadMethod(fullurl);
		if(headers != null){
			for (Map.Entry<String, String> en : headers.entrySet()) {
				method.addRequestHeader(en.getKey(), en.getValue());
			}
		}
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			code = client.executeMethod(method);
			if(isValidHttpCode(code)){
				return method.getResponseHeaders();
			}else{
				log.error("Http response code(" + code + ") is not 200 when get " + fullurl + " with parameters " + params);
			}
		} catch (HttpException e) {
			log.error("Unexpected HttpException when http get " + fullurl + " with parameters " + params, e);
		} catch (IOException e) {
			log.error("Unexpected IOException when http get " + fullurl + " with parameters " + params, e);
		} finally{
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	private static final Set<Integer> INVALID_HTTP_CODES = Sets.newHashSet(499, 500, 502, 403, 404);
	public static final boolean isValidHttpCode(int code){
		return !INVALID_HTTP_CODES.contains(code);
	}

	public static String buildGetUrl(String url, Map<String, String> params) {
		return params == null || params.isEmpty() ? url : new StringBuilder(256).append(url).append("?").append(urlencode(params)).toString();
	}
	
	public static final Map<String, Object> httpPostToMap(String url, Map<String, String> params){
		String response = HttpClientUtils.httpPost(url, params);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Map<String ,Object> responseMap = JSONUtil.decodeJsonToMap(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final List<Object> httpPostToList(String url, Map<String, String> params){
		String response = HttpClientUtils.httpPost(url, params);
		if(response == null){
			log.error("Failed to http post "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		List<Object> responseMap = JSONUtil.decodeJsonToList(response);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+url+ " with post parameters " + params + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final Object httpGetToObject(String url, Map<String, String> params){
		String response = HttpClientUtils.httpGet(url, params, null);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Object responseMap = JSONUtil.decodeJson(response, Object.class);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final Object httpPostToObject(String url, Map<String, String> params){
		final String response = HttpClientUtils.httpPost(url, params);
		if(response == null){
			log.error("Failed to http get "+HttpClientUtils.buildGetUrl(url, params) + " for 3 times, please check your network");
			return null;
		}
		Object responseMap = JSONUtil.decodeJson(response, Object.class);
		if(responseMap == null){
			log.error("Some error happens when getting json from "+HttpClientUtils.buildGetUrl(url, params) + ", the repsonse is " + response);
			return null;
		}
		return responseMap;
	}
	
	public static final String httpPost(String url, Map<String, String> params){
		HttpClient client = new HttpClient();
		return httpPost(url, params, null, client);
	}
	
	public static final String httpPost(String url, Map<String, String> params, HttpClient client){
		return httpPost(url, params, null, client);
	}
	
	public static final String httpPost(String url, Map<String, String> params, Map<String, String> headers){
		HttpClient client = new HttpClient();
		return httpPost(url, params, headers, client);
	}
	
	
	public static final String httpPost(String url, StringRequestEntity entity){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestEntity(entity);
		method.addRequestHeader("Content-Type", "application/json");
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");  
			method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "UTF-8");
			code = client.executeMethod(method);
			if(isValidHttpCode(code)){
				return readFullResponseBody(method);
			}else{
				log.error("Http response code(" + code + ") is not 200 when post " + url + " with parameters " + entity);
			}
		} catch (HttpException e) {
			log.error("Http response code(" + code + ") is not 200 when post " + url + " with parameters " + entity);
		} catch (IOException e) {
			log.error("Http response code(" + code + ") is not 200 when post " + url + " with parameters " + entity);
		} finally{
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final String httpPost(String url, Map<String, String> params, Map<String, String> headers, HttpClient client){
		PostMethod method = new PostMethod(url);
		if(params != null){
			for (Map.Entry<String, String> en : params.entrySet()) {
				if(en.getValue() != null){
					method.addParameter(en.getKey(), en.getValue());
				}
			}
		}
		if(headers != null){
			for (Map.Entry<String, String> en : headers.entrySet()) {
				method.addRequestHeader(en.getKey(), en.getValue());
			}
		}
		method.addRequestHeader("User-Agent", USER_AGENT);
		long timestamp = System.currentTimeMillis();
		int code = UNKNOWN_HTTP_CODE;
		try {
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");  
			method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "UTF-8");
			code = client.executeMethod(method);
			if(isValidHttpCode(code)){
				return readFullResponseBody(method);
			}else{
				String fullurl = buildGetUrl(url, params);
				log.error("Http response code(" + code + ") is not 200 when post " + fullurl + " with parameters " + params);
			}
		} catch (HttpException e) {
			String fullurl = buildGetUrl(url, params);
			log.error("Unexpected HttpException when http post " + fullurl + " with parameters " + params, e);
		} catch (IOException e) {
			String fullurl = buildGetUrl(url, params);
			log.error("Unexpected IOException when http post " + fullurl + " with parameters " + params, e);
		} finally{
			RequestCounter.instance.count(url, code, (int)(System.currentTimeMillis() - timestamp));
		}
		return null;
	}
	
	public static final String readFullResponseBody(HttpMethod method){
		BufferedReader reader = null;
		
		try{
			StringBuilder sb = new StringBuilder(256);
			reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
			for(String line = reader.readLine(); line != null; line = reader.readLine()){
				sb.append(line).append('\n');
			}
			return sb.toString().trim();
		}catch(IOException ex){
			log.error("Unexpected IOException when read http response", ex);
			return null;
		}
	}
	
	public static final String urlencode(String value){
		if(StringUtils.isBlank(value)){
			return "";
		}
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Fail to encode string " + value + " with UTF-8");
			return value;
		}
		
	}
	
	public static final String urlencode(Map<String, String> params){
		if(params == null || params.isEmpty()){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> en : params.entrySet()){
			sb.append(urlencode(en.getKey())).append("=").append(urlencode(en.getValue())).append("&");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	public static final boolean isValidLink(String link){
		
		URL url;
		try {
			 url = new URL(link);
			 HttpURLConnection connt = (HttpURLConnection)url.openConnection();
			 connt.setRequestMethod("HEAD");
			 String strMessage = connt.getResponseMessage();
			 if (strMessage.compareTo("Not Found") == 0) {
				 return false;
			 }
			 connt.disconnect();
		} catch (MalformedURLException e) {
			log.error("url is not valid:" + link);
			return false;
		} catch (IOException e) {
			log.error("url is not valid:"+ link);
			return false;
		}
		return true;
	}
	
}
