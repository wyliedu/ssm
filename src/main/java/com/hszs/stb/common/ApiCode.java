package com.hszs.stb.common;


public final class ApiCode{
	
	public static final String CODE_ATTRIBUTE = "com.passport.code";
	
	
	public static final String SUCCESS = "A00000";//成功
	
	public static final String ERR_WRONG_ATOKEN =	"E00001";		//非法的鉴权token
	public static final String ERR_PWD_CHANGED =	"E00002";		//用户密码已经修改
	public static final String ERR_WRONG_PARAMS =	"E00003";		//输入参数有误
	public static final String ERR_API_NOT_APPLICABLE = "E000004";  // API不适用当前账号
	
	public static final String ERR_USER_EXITS 	=	"E00100";		//用户已经存在
	public static final String ERR_USER_NONEXITS =	"E00101";		//用户不存在
	public static final String ERR_PWD_NOTMATCH =	"E00102";		//用户已经在另外一个设备上登录
	public static final String ERR_USER_INVALID =	"E00103";
	public static final String ERR_DEVICE_NONEXISTS ="E00110";


	
	public static final String ERR_UNKNOWN_ERROR =	"E00500";	   //未知错误
	public static final String ERR_NO_AUTHORITY =	"E00403";
	public static final String ERR_NO_EXIST =	"E00404";
	
	public static final String ERR_PAGE_NOT_EXISTS ="E00404";
	public static final String ERR_ZONE_NOT_EXISTS ="E00405";
	
	public static final String ERR_MEETING_NOT_EXISTS ="E00406";
	public static final String ERR_MEETING_ENDED ="E00407";

	public static final String ERR_ORDER_NOT_EXISTS = "E00408";    // 工单不存在


}