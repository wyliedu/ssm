package com.hszs.stb.model.rly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hszs.stb.model.api.AbstractJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubAccount extends AbstractJsonModel{

	private static final long serialVersionUID = 4682996491056110139L;
	//{"voipAccount":"88857900000002","dateCreated":"2015-08-29 15:04:26","voipPwd":"r3KrPEAO","subToken":"f4454e93de148979e30e07346e60dc43","subAccountSid":"2f98eb514e1c11e58b68ac853d9d52fd"}
	
	public String voipAccount;
	public String dateCreated;
	public String voipPwd;
	public String subToken;
	public String subAccountSid;
	
}
