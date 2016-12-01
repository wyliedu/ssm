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


package com.hszs.stb.common;

import org.apache.commons.lang.StringUtils;


/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {
	
	public static final String QIYI_SSO_SECRET;
	public static final String QIYI_SSO_DOMAIN = ".iqiyi.com";
	
	public static final String PPS_SSO_DOMAIN = ".pps.tv";
	
	public static final String SESSION_COOKIE_KEY = "WMSID";
	public static final String WM_SSO_DOMAIN = ".wm.iqiyi.com";
	
	
	public static final int SESSION_TIMEOUT = 10 * 60 * 1000; //10分钟
	
	public static final String QIYI_SSO_LOGINURL = "http://www.qiyi.com";
	static{
		if(StringUtils.equalsIgnoreCase(System.getenv("dev"), "true")){
			QIYI_SSO_SECRET = "qiyi_sso_2010_01_28_767a6227d84653e9";
		}else{
			QIYI_SSO_SECRET = "qiyi_sso_2011_03_14_9b0590eb4c182994";
		}
	}
	
	
	//////////////////////////////Generate Code Below///////////////////////

	public static final String PR_VERSION="V1.0.28";
	
	public static final String PLAY_RECORD_VERSION = PR_VERSION+"_20121107";
	
    private Constants() {
        // hide me
    }
    //~ Static fields/initializers =============================================

    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     */
    public static final String CSS_THEME = "csstheme";
    
    /**
     */
    public static final String A00000 = "A00000";
    
    
    
    
    
    public static final String PR_REQUEST_FROM_COLD ="cold";
    
    public static final String PR_REQUEST_FROM_HOT ="hot";
    
    public static final String PR_REQUEST_FROM_NAME ="pr_from";
    
    public static final String LOG_SLOW="slow";
    
    public static final String LOG_EXCEPTION="pr_exception";
    
    public static final String LOG_UPDATE_PLAY_RECORD="update_play_record";
    
    public static final String UNLOGIN_LOG_UPDATE_PLAY_RECORD="unlogin_update_play_record";
    
    public static final String LOG_ACTION_PLAY_RECORD="action_play_record";
    
    public static final String LOG_MC_MISS="mc_miss";
    
    public static final String LOG_DATA_NOT_FOUND="data_not_found";
    
    public static final String LOG_FOR_DEBUG="for_debug";
    
    public static final String MC_CODE_LOGIN="mc_login";
    
    public static final String MC_CODE_UN_LOGIN="mc_un_login";
    
    public static final long TIME_FOR_RECORD_SLOW_LOG = 1000 ; 
    
    public static final long TIME_FOR_MEMCACHED_SLOW_LOG = 300 ; 
    
    public static final int PR_SKIP = 0;
    
    public static final int PR_PAGE_SIZE = 200;
    
    
    public static final int ONE_MINUTE= 1000 * 60;
    
    public static final long ONE_MONTH = ONE_MINUTE * 60 * 24 *30 ;
    
    public static final long HALF_ONE_HOUR_MILLIS =  30 * 60 * 1000L;
    
    public static final int REASONABLE_PLAYRECORD_SIZE = 100; 
    
    public static final int DEFAULT_TERMINAL_ID_PC=11;
    
    
    
    
    public static final String OBJECT_ID_CONNECTOR = "_";
    
    public static final int CHANNEL_ZY = 6 ; 
    
    public static final int CHANNEL_SH = 14; 
    
    
    
    public static final String VRS_DISK_CACHE_NAME ="vrs_cache";
    
    public static final boolean PROFILE_IS_EMAIL = false;
    public static final boolean PROFILE_IS_PHONE = true;
    
    public static final byte PROFILE_ACTIVED = 1;
    public static final byte PROFILE_INACTIVED = 0;
    
    public static final String PROFILE_SAFE_PASS_KEY = "iQ1yip9A";
    public static final String PROFILE_PASS_KEY = "qiyi_psk_2010_08_05_4c5a1875201";
    public static final String PROFILE_ACODE_KEY = "9fc86923805e87438c6f7f137a89fe89";
    public static final String PROFILE_BCODE_KEY = "9d7528e3356d0289fad5db5514695c55";
    
    public static final String QY_SERVER_NAME = "passport.qiyi.com";
    public static final String IQY_SERVER_NAME = "passport.iqiyi.com";
    
    public static final String PPS_APP_KEY="10021";
    public static final String PPS_APP_SECRET="33bfb6bb453d0233536b1bab7d877e36";
    
    //An expiration time, in seconds. Can be up to 30 days. After 30 days, is treated as a unix timestamp of an exact date.
    public static final int CACHE_TIMEOUT_USER_PROFILE=3600 * 24 * 29;
    public static final String CACHE_PREFIX_PROFILE="uid_";
    public static final String CACHE_PREFIX_UNAME="uname_";
    public static final String CACHE_PREFIX_NICKNAME="nk_";
    public static final String CACHE_PREFIX_DOMAIN="domain_";
    public static final String CACHE_PREFIX_U_DOMAIN="udo_";
    public static final String CACHE_PREFIX_U_FGTW="ufgtw_";
    public static final String CACHE_PREFIX_BDPU = "bdpu_";
    
    public static final String CACHE_PREFIX_PUID="puid_";
    public static final String CACHE_PREFIX_SUID="suid_";
    
    public static final String CACHE_PERFIX_VIPINFO="vip_";
    public static final String CACHE_PERFIX_USERNFO="user_";
    
    public static final String CACHE_PREFIX_PPS_FIRSTLOGIN="ppslogin_";
    
    public static final String CACHE_PERFIX_TODAY_LOGIN ="login_";
    public static final String CHACHE_LONG_LOGIN ="llg_";
    
    //缓存开关，是否只使用缓存数据
    public static final boolean USE_CACHE_ONLY=false; 
    
    public static final boolean USE_LOCAL_PPS_DATA=true;
    
    public static final int CACHE_TIMEOUT_CELLPHONE_LIMIT=86400;
    
    public static final String USE_SCRIBE_LOG = "USE_SCRIBE_LOG";
    
    public static final String SCRIBE_CLIENT_IPS = "SCRIBE_CLIENT_IPS";
    
    public static final String AUDIT_CONTENT = "AUC_";
    
    public static final String USERINFO_FIELDS_REQUEST_ATTR = "UI_FIELDS";
    
    public static final String USER_CREDIT_REQUEST_CACHE_KEY = "USER_CREDIT_CACKE_KEY";
    
    public static final String LOG_LOGIN_REG_INFO = "LOG_LOGIN_REG_INFO";
    public static final String LOG_LOGIN_REG_FRM = "LOG_LOGIN_REG_FRM";
 
    public static final int AGENTTYPE = 110;
    
    
    /**
	 * VRS RPC 超时阈值
	 */
	public static final int VRS_RPC_REQUESRT_TIME_OUT_THRESHOLD = 50;
	
	/*
	 * 支持订阅和推送的VRS频道ID
	 */
	public static final int VRSCHANNEL_ID_ALL = 0;// 全部
	public static final int VRSCHANNEL_ID_DIANYING = 1;// 电影
	public static final int VRSCHANNEL_ID_DIANSHIJU = 2;// 电视剧
	public static final int VRSCHANNEL_ID_JILUPIAN = 3;// 纪录片
	public static final int VRSCHANNEL_ID_DONGMAN = 4;// 动漫
	public static final int VRSCHANNEL_ID_YINYUE = 5;// 音乐
	public static final int VRSCHANNEL_ID_ZONGYI = 6;// 综艺
	public static final int VRSCHANNEL_ID_YULE = 7;// 娱乐
	

	public static final int VRSCHANNEL_ID_TRAVEL=9; //旅游
	public static final int VRSCHANNEL_ID_PIANHUA = 10;// 片花
	public static final int VRSCHANNEL_ID_EDUCATION=12; //旅游
	public static final int VRSCHANNEL_ID_ARTIST = -1;// 艺人页
	public static final int VRSCHANNEL_ID_LISTPAGE = -2;// 列表页
	public static final int VRSCHANNEL_ID_FASION=13;
	public static final int VRSCHANNEL_ID_FASION_VARITY = 14; //时尚综艺
	

	public static final int VRSCHANNEL_ID_CHILDREN = 15; //少儿综艺
    
	/*
	 * 订阅实体类型
	 */
	public static final int SUBSCRIBE_ENTITY_ALBUM = 1;// 专辑
	public static final int SUBSCRIBE_ENTITY_SOURCE = 2;// 来源
	public static final int SUBSCRIBE_ENTITY_PIANHUA_KEYWORD = 3;// 片花关键词
}
