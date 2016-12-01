package com.hszs.stb.model.rly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hszs.stb.model.api.AbstractJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SMSResponse extends AbstractJsonModel {

	private static final long serialVersionUID = 9222019120999596378L;
	
	public String statusCode;
	
	public TemplateSMS templateSMS;

	//{"templateSMS":{"dateCreated":"20151011153450","smsMessageSid":"2015101115345021648282"},"statusCode":"000000"}
	public static class TemplateSMS extends AbstractJsonModel {

		private static final long serialVersionUID = -4221268247057706448L;
		
		public String dateCreated;
		
		public String smsMessageSid;
	}
}
