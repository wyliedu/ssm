package com.hszs.stb.model.api;

public class KickoffTransmission extends AbstractJsonModel {
	private static final long serialVersionUID = 1L;
	
	public KickoffTransmission(){}
	
	public KickoffTransmission(int uid, String newDeviceId, String curDeviceId){
		this.uid = uid;
		this.newDeviceId = newDeviceId;
		this.logoutDeviceId = curDeviceId;
	}
	
	public int uid;
	public String newDeviceId;
	public String logoutDeviceId;
}