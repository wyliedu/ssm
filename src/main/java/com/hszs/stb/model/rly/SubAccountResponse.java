package com.hszs.stb.model.rly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hszs.stb.model.api.AbstractJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubAccountResponse extends AbstractJsonModel {

	private static final long serialVersionUID = 9222019120999596378L;
	
	public String statusCode;
	
	public SubAccount SubAccount;

}
