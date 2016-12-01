package com.hszs.stb.service.services.api;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hszs.stb.common.helper.CodecHelper;
import com.hszs.stb.common.helper.HttpClientUtils;
import com.hszs.stb.common.helper.JSONUtil;
import com.hszs.stb.common.helper.UnitTestHelper;
import com.hszs.stb.common.util.DateHelper;
import com.hszs.stb.common.util.HttpClientManager;
import com.hszs.stb.common.util.user.MapBuilder;
import com.hszs.stb.model.rly.ResponseCode;
import com.hszs.stb.model.rly.RonglianyunConfig;
import com.hszs.stb.model.rly.SMSResponse;
import com.hszs.stb.model.rly.SMSResponse.TemplateSMS;
import com.hszs.stb.model.rly.SubAccount;
import com.hszs.stb.model.rly.SubAccountResponse;



@Service
public class RonglianyunService {
	
	private static final Logger logger=LoggerFactory.getLogger(RonglianyunService.class);
	
	final HttpClient httpClient = new HttpClientManager().connectionTimeout(10000).soTimeout(10000).defaultMaxConnectionsPerHost(4).maxTotalConnections(8).getHttpClient();


	//@Autowired RonglianyunConfig ronglianyunConfig;
	
	public final String URL = "https://app.cloopen.com:8883/2013-12-26";
	//public final String URL = "https://sandboxapp.cloopen.com:8883/2013-12-26";
	
	
	
	
	public SubAccount createSubAccount(int uid) {
		final String url = String.format("%s/Accounts/%s/SubAccounts?sig=", URL, RonglianyunConfig.accountSid);
		final PostMethod method = this.createMainAccountPost(url);
		
		Map<String, Object> params = new MapBuilder<String, Object>()
				.put("appId", RonglianyunConfig.appId)
				.put("friendlyName", String.valueOf(uid))
				.build();
		final String response = execute(method, params);
		final SubAccountResponse subaResponse = JSONUtil.decodeJson(response, SubAccountResponse.class);
		if(subaResponse != null) {
			if(StringUtils.equals(subaResponse.statusCode, ResponseCode.SUCC.code)) {
				return subaResponse.SubAccount;
			}else if(StringUtils.equals(subaResponse.statusCode, ResponseCode.DUP_SUBACCOUNT.code)) {
				return getSubAccount(uid);
			}
		}else{
			logger.error("无法从荣联运注册亚账号：{}", uid);
		}
		return null;
	}
	
	public SubAccount getSubAccount(int uid) {
		
		final String url = String.format("%s/Accounts/%s/QuerySubAccountByName?sig=", URL, RonglianyunConfig.accountSid);
		final PostMethod method = this.createMainAccountPost(url);
		
		Map<String, Object> params = new MapBuilder<String, Object>()
				.put("appId", RonglianyunConfig.appId)
				.put("friendlyName", String.valueOf(uid))
				.build();
		final String response = execute(method, params);
		final SubAccountResponse subaResponse = JSONUtil.decodeJson(response, SubAccountResponse.class);
		if(subaResponse != null && StringUtils.equals(subaResponse.statusCode, ResponseCode.SUCC.code)) {
			return subaResponse.SubAccount;
		} else {
			logger.error("从荣联运查询亚账号失败：{}", uid);
		}
		return null;
	}
	
	public PostMethod createMainAccountPost(String url) {
		return createMethod(url, RonglianyunConfig.accountSid, RonglianyunConfig.authToken);
	}
	
	public PostMethod createSubAccountPost(String url, String subAccounSid, String token) {
		return createMethod(url, subAccounSid, token);
	}
	
	private PostMethod createMethod(String url, String accountSid, String authtoken) {
		final String timestamp = DateHelper.getFormatDate(new Date(), "yyyyMMddHHmmss");
		final String authRawdata = new StringBuilder(100).append(accountSid).append(":").append(timestamp).toString();
		final String sigRawdata = new StringBuilder(100).append(accountSid).append(authtoken).append(timestamp).toString();
		final String sign = CodecHelper.md5(sigRawdata);
		final String auth = CodecHelper.base64Encode(authRawdata);
		
		final PostMethod method = new PostMethod(url + sign);
		method.addRequestHeader("Accept", "application/json");
		method.addRequestHeader("Content-Type", "application/json;charset=utf-8");
		method.addRequestHeader("Authorization", auth);
		return method;
	}

	
	public String execute(PostMethod method, Map<String, Object> params) {
		int code = 0;
		String body = JSONUtil.jsonEncode(params);
		
		try {
			StringRequestEntity requestBody = new StringRequestEntity(body, "application/json", "utf-8");
			method.setRequestEntity(requestBody);
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");  
			method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "UTF-8");
			code = httpClient.executeMethod(method);
			if(code == 200){
				return HttpClientUtils.readFullResponseBody(method);
			}else{
				logger.error("{},{}", method, params);
			}
		} catch (HttpException ex) {
			logger.error("{},{}", new Object[]{method, params}, ex);
		} catch (IOException ex) {
			logger.error("{},{}", new Object[]{method, params}, ex);
		}
		return null;
	}
	
	public TemplateSMS sendVerifyCodeSMS(String cellphone, String code) {
		if(UnitTestHelper.isUnitTestRunning()) {
			return new TemplateSMS();
		}
		final String templateId = "104023";
		final String url = String.format("%s/Accounts/%s/SMS/TemplateSMS?sig=", URL, RonglianyunConfig.accountSid);
		final PostMethod method = this.createMainAccountPost(url);
		
		Map<String, Object> params = new MapBuilder<String, Object>()
				.put("appId", RonglianyunConfig.appId)
				.put("to", cellphone)
				.put("templateId", templateId)
				.put("datas", new Object[]{ code })
				.build();
		final String response = execute(method, params);
		final SMSResponse subaResponse = JSONUtil.decodeJson(response, SMSResponse.class);
		if(subaResponse != null) {
			if(StringUtils.equals(subaResponse.statusCode, ResponseCode.SUCC.code)) {
				return subaResponse.templateSMS;
			}
		}
		logger.error("发送短信失败：{}", response);
		return null;
	}
	
}
