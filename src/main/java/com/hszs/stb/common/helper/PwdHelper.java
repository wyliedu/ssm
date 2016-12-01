package com.hszs.stb.common.helper;


public final class PwdHelper {

	public static final String PWD_SALT = "eJ&u";
	
	public static final String hash(String account, String password) {
		return CodecHelper.md5(password);
	}
	
	public static final String safeHash(String account, String password) {
		return CodecHelper.md5(account + PWD_SALT + password);
	}
}
