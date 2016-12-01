package com.hszs.stb.common.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hszs.stb.common.Constants;



/**
 * Convenience class for setting and retrieving cookies.
 */
public final class RequestHelper {
	
    private static final Log log = LogFactory.getLog(RequestHelper.class);
    
    private static String PREFIX = "\\u";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private RequestHelper() {
    }
    

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name the name of the cookie
     * @param value the value of the cookie
     * @param path the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path
    		, String domain, int maxAge) {
    	if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setVersion(0);
        
        cookie.setMaxAge(maxAge); // 30 days
        response.addCookie(cookie);
    }
    
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
    	setCookie(response, name, value, "/", Constants.QIYI_SSO_DOMAIN, maxAge);
    }
    
    public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, "/", Constants.QIYI_SSO_DOMAIN, Integer.MAX_VALUE);
	}
    
    public static String getCookieDomain(HttpServletRequest request){
    	if(StringUtils.endsWithIgnoreCase(request.getServerName(), ".pps.tv")){
    		return Constants.PPS_SSO_DOMAIN;
    	}
    	return Constants.QIYI_SSO_DOMAIN;
    }

    public static void setWmCookie(HttpServletResponse response, String name, String value) {
    	setCookie(response, name, value, "/", Constants.WM_SSO_DOMAIN, Constants.SESSION_TIMEOUT);
    }
    
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
    	setCookie(response, name, value, "/", getCookieDomain(request), maxAge);
    }
    
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, "/", getCookieDomain(request), Integer.MAX_VALUE);
	}

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name the name of the cookie to find
     *
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (final Cookie thisCookie : cookies) {
            if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
                returnCookie = thisCookie;
                break;
            }
        }

        return returnCookie;
    }
    
    /**
     * 判断是否为搜索引擎
     * 
     * @param req
     *            HttpServletRequest
     * @return ture:robot, false,is not robot
     */
    public static boolean isRobot(HttpServletRequest req) {
        String ua = req.getHeader("user-agent");
        if (StringUtils.isBlank(ua))
            return false;
        return (ua != null && (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1 || ua.indexOf("sogou") != -1 || ua.indexOf("sina") != -1 || ua.indexOf("iaskspider") != -1 || ua.indexOf("ia_archiver") != -1 || ua.indexOf("Sosospider") != -1 || ua.indexOf("YoudaoBot") != -1 || ua.indexOf("yahoo") != -1 || ua.indexOf("yodao") != -1 || ua.indexOf("MSNBot") != -1 || ua.indexOf("spider") != -1 || ua.indexOf("Twiceler") != -1 || ua.indexOf("Sosoimagespider") != -1 || ua.indexOf("naver.com/robots") != -1 || ua.indexOf("Nutch") != -1 || ua.indexOf("spider") != -1));
    }
    
    /**
     * 获取客户端IP地址，支持proxy
     * 
     * @param req
     *            HttpServletRequest
     * @return IP地址
     */
    public static String getRemoteAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip)) {
        	ip = req.getHeader("x-forwarded-for");
        }
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if (ips != null) {
                for (String tmpip : ips) {
                    if (StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if (isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)) {
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if (isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if (ip.indexOf('.') == -1)
            ip = "127.0.0.1";
        return ip;
    }
    
    /**
     * 判断字符串是否是一个IP地址
     * 
     * @param addr
     *            字符串
     * @return true:IP地址，false：非IP地址
     */
    public static boolean isIPAddr(String addr) {
        if (StringUtils.isEmpty(addr))
            return false;
        String[] ips = StringUtils.split(addr, '.');
        if (ips.length != 4)
            return false;
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
        } catch (Exception e) {
        }
        return false;
    }

    public static String getRemotePrivateAddr(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip)) {
            String[] ips = StringUtils.split(ip, ',');
            if (ips != null) {
                for (String tmpip : ips) {
                    if (StringUtils.isBlank(tmpip))
                        continue;
                    tmpip = tmpip.trim();
                    if (isIPAddr(tmpip)) {
                        return tmpip.trim();
                    }
                }
            }
        }
        ip = req.getHeader("x-real-ip");
        if (isIPAddr(ip))
            return ip;
        ip = req.getRemoteAddr();
        if (ip.indexOf('.') == -1)
            ip = "127.0.0.1";
        return ip;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response,
                                    Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }
    
    /**
     * 
     * @param response response the current web response
     * @param name the cookie's name to delete
     * @param path the cookie's path to delete
     * @param domain the cookie's domain to delete
     */
    public static void deleteCookie(HttpServletResponse response,
            String name, String path, String domain) {
		if (StringUtils.isNotEmpty(name)) {
			// Delete the cookie by setting its maximum age to zero
			Cookie cookie = new Cookie(name, "");
			cookie.setDomain(domain);
			cookie.setMaxAge(0);
			cookie.setPath(path);
			response.addCookie(cookie);
		}
    }
    
    /**
     * 
     * @param response response the current web response
     * @param name the cookie's name to delete
     * @param path the cookie's path to delete
     * @param domain the cookie's domain to delete
     */
    public static void deleteCookie(HttpServletResponse response,
            String name) {
    	deleteCookie(response, name, "/", Constants.QIYI_SSO_DOMAIN);
    }

    /**
     * Convenience method to get the application's URL based on request
     * variables.
     * 
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {
        if (request == null) return "";
        
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    
    /**
     * Check whether it's a Post request
     * @return
     */
    public static boolean isPostRequest(HttpServletRequest request){
    	return StringUtils.equalsIgnoreCase("post", request.getMethod());
    }
    
    /**
     * Check whether it's a Get request
     * @return
     */
    public static boolean isGetRequest(HttpServletRequest request){
    	return StringUtils.equalsIgnoreCase("get", request.getMethod());
    }
    
    public static String getHttpRoot(HttpServletRequest request){
    	if(StringUtils.equals("passport.qiyi.com", request.getServerName()) || StringUtils.equals("passport.qiyi.domain", request.getServerName())){
    		return "http://passport.qiyi.com";
    	}else{
    		return "http://passport.iqiyi.com";
    	}
    }
    
    public static String getOtherPassportDomain(HttpServletRequest request){
    	if(StringUtils.equals("passport.iqiyi.com", request.getServerName())){
    		return "http://passport.qiyi.com";
    	}else{
    		return "http://passport.iqiyi.com";
    	}
    }
    
    public static boolean isInternalInvoke(HttpServletRequest request){
    	return StringUtils.equals("passport.qiyi.domain", request.getServerName());
    }
    
    public static String jsonpOutput(HttpServletRequest request,
			HttpServletResponse response, Object obj){
		String output;
		if(obj == null){
			output = "null";
		}else if(obj instanceof String){
			output = obj.toString();
		}else{
			output = JSONUtil.jsonEncode(obj);
		}
		response.addHeader("Content-Type", "application/x-javascript; charset=utf-8");
		String callback = request.getParameter("cb");
		if(StringUtils.isBlank(callback)){
			return output;
		}else{
			StringBuilder sb = new StringBuilder();
			sb.append("var ").append(callback).append(" = ").append(output);
			return sb.toString();
		}
	}
    
    public static boolean isPHPStyleRequest(HttpServletRequest request){
    	return StringUtils.isNotBlank(request.getParameter("__PHP"));
    }
    
	public static String getHost(String urlString) {
		if(StringUtils.isBlank(urlString)) {
			return null;
		}
		URL url;
		try {
			url = new URL(urlString);
			return url.getHost();
		} catch (MalformedURLException e) {
			return urlString;//解析不出来直接返回
		}
	}
    
    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return long
     */
    public static Double getDoubleParam(HttpServletRequest req, String param, Double defaultValue) {
    	String value = RequestHelper.getParam(req, param, null);
    	if(value!=null){
    		return NumberUtils.toDouble(value);
    	}
    	
    	return defaultValue;
    	
    }

    /**
     * 获取浏览器提交的字符串参数
     * @param req HttpServletRequest
     * @param param 参数名
     * @param defaultValue 默认值
     * @return String
     */
    public static String getParam(HttpServletRequest req, String param, String defaultValue) {
        String value = req.getParameter(param);
        return (StringUtils.isEmpty(value)) ? defaultValue : value;
    }
    
    /**
    * 获取浏览器提交的字符串参数
    * @param req HttpServletRequest
    * @param param 参数名
    * @param defaultValue 默认值
    * @return String
    */
   public static String getParam(HttpServletRequest req, String[] params, String defaultValue) {
	   for(String param : params){
		   String value = req.getParameter(param);
		   if(StringUtils.isBlank(value)){
			   continue;
		   }
		   return value;
	   }
	   return defaultValue;
   }
    
    /**
     * 获取HTTP端口
     * 
     * @param req
     *            HttpServletRequest
     * @return 端口数值
     * @throws java.net.MalformedURLException
     */
    public static int getHttpPort(HttpServletRequest req) {
        try {
            return new URL(req.getRequestURL().toString()).getPort();
        } catch (MalformedURLException excp) {
            return 80;
        }
    }

    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return int
     */
    public static int getParam(HttpServletRequest req, String param, int defaultValue) {
        return NumberUtils.toInt(req.getParameter(param), defaultValue);
    }
    
    /**
     * @param req
     * @param param
     * @return the int value; or null
     */
    public static Integer getIntParam(HttpServletRequest req, String param){
    	try{
    		String value = RequestHelper.getParam(req, param, null);
    		if(StringUtils.isNotEmpty(value)){
    			return Integer.parseInt(value);
    		}
    	}catch(Exception e){
    	}
    	return null;
    }
    
    /**
     * @param req
     * @param param
     * @return the long value; or null
     */
    public static Long getLongParam(HttpServletRequest req,String param){
    	
    	try{
    		String value = RequestHelper.getParam(req, param, null);
    		if(StringUtils.isNotEmpty(value)){
    			return Long.parseLong(value);
    		}
    	}catch(Exception e){
    		
    	}
    	return null;
    	
    }
    
    /**
     * 获取浏览器提交的整形参数
     * 
     * @param req
     *            HttpServletRequest
     * @param param
     *            参数名
     * @param defaultValue
     *            默认值
     * @return long
     */
    public static long getParam(HttpServletRequest req, String param, long defaultValue) {
        return NumberUtils.toLong(req.getParameter(param), defaultValue);
    }
    
    public static long getQipaVideoDuration(HttpServletRequest req, String param, long defaultValue){
    	
    	try{
    		String value=req.getParameter(param);
    		if(value!=null){
    			Double rawValue=Double.parseDouble(value);
        		return rawValue.longValue();
        	}
    	}catch(Exception e){
    	}
    
    	return defaultValue;
    	
    }
    
    /**
     * 对getParameterValues的封装，转换成long数组
     * 
     * @param req
     *            HttpServletRequest
     * @param name
     *            参数名
     * @return long数组
     */
    public static long[] getParamValues(HttpServletRequest req, String name) {
        String[] values = req.getParameterValues(name);
        if (values == null)
            return null;
        return (long[]) ConvertUtils.convert(values, long.class);
    }
    
    public static boolean isURL(String urlStr){
    	if(StringUtils.isBlank(urlStr)){
    		return false;
    	}
    	try {
			URL url = new URL(urlStr);
			return StringUtils.equalsIgnoreCase(url.getProtocol(), "http") || StringUtils.equalsIgnoreCase(url.getProtocol(), "https");
		} catch (MalformedURLException e) {
			return false;
		}
    	
    }
    
    
    private static final Pattern IP_PATTERN = Pattern.compile( "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$" );
    
    public static String getRootDomain(HttpServletRequest request){
    	final String domain = request.getServerName();
    	if(IP_PATTERN.matcher(domain).matches()){
    		return domain;
    	}
    	String[] chunks = StringUtils.split(domain, '.');
    	if(chunks == null || chunks.length < 2){
    		return domain;
    	}
    	return new StringBuilder().append(chunks[chunks.length - 2]).append(".").append(chunks[chunks.length - 1]).toString();
    }
    
    public static String native2Ascii(String str) {
        StringBuffer sb = new StringBuffer(str.length());
        sb.setLength(0);
        for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                sb.append(native2Ascii(c));
        }
        return sb.toString();
    }
    
    /** 
     * Ascii to native string. It's same as execut native2ascii.exe -reverse. 
     *  
     * @param str 
     *            ascii string 
     * @return native string 
     */  
    public static String ascii2Native(String str) {  
        StringBuilder sb = new StringBuilder();  
        int begin = 0;  
        int index = str.indexOf(PREFIX);  
        while (index != -1) {  
            sb.append(str.substring(begin, index));  
            sb.append(ascii2Char(str.substring(index, index + 6)));  
            begin = index + 6;  
            index = str.indexOf(PREFIX, begin);  
        }  
        sb.append(str.substring(begin));  
        return sb.toString();  
    } 
    
    /** 
     * Ascii to native character. 
     *  
     * @param str 
     *            ascii string 
     * @return native character 
     */  
    private static char ascii2Char(String str) {  
        if (str.length() != 6) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must be 6 character.");  
        }  
        if (!PREFIX.equals(str.substring(0, 2))) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must start with \"\\u\".");  
        }  
        String tmp = str.substring(2, 4);  
        int code = Integer.parseInt(tmp, 16) << 8;  
        tmp = str.substring(4, 6);  
        code += Integer.parseInt(tmp, 16);  
        return (char) code;  
    }

	private static StringBuffer native2Ascii(char charater) {
	        StringBuffer sb = new StringBuffer();
	        if (charater > 255) {
	                sb.append("\\u");
	                int lowByte = (charater >>> 8);
	                sb.append(int2HexString(lowByte));
	                int highByte = (charater & 0xFF);
	                sb.append(int2HexString(highByte));
	        } else {
	                sb.append(charater);
	        }
	        return sb;
	}
	
	private static String int2HexString(int code) {
	        String hexString = Integer.toHexString(code);
	        if (hexString.length() == 1)
	                hexString = "0" + hexString;
	        return hexString;
	}
	
	public static boolean isValidQiyiURL(String urlStr){
		if(StringUtils.startsWithIgnoreCase(urlStr, "http://") || StringUtils.startsWithIgnoreCase(urlStr, "https://")){
			try {
				URL url = new URL(urlStr);
				return StringUtils.indexOfAny(url.getHost(), new String[]{".qiyi.com", ".iqiyi.com", ".pps.tv"}) > 0;
			} catch (MalformedURLException e) {
				return false;
			}
			
		}else{
			return StringUtils.startsWith(urlStr, "/");
		}
	}
	
	public static String filterValidIqiyiCallback(String urlStr){
		if(StringUtils.isBlank(urlStr)){
			return "";
		}
		try {
			URL url = new URL(urlStr);
			if(StringUtils.endsWith(url.getHost(), ".iqiyi.com") || StringUtils.endsWith(url.getHost(), ".qiyi.com") || StringUtils.endsWith(url.getHost(), ".pps.tv")){
				return url.toString();
			}
		} catch (MalformedURLException e) {
		}
		return "";
	}
	
	public static Map<String, String> getUrlParameters(String url)  {
	    String[] urlParts = url.split("\\?");
	    if (urlParts.length > 1) {
	        return getUriParameters(urlParts[1]);
	    }
	    return null;
	}
	
	public static Map<String, String> getUriParameters(String uri)  {
		Map<String, String> params = new HashMap<String, String>();
		for (String param : uri.split("&")) {
        	try{
	            String pair[] = param.split("=");
	            String key = URLDecoder.decode(pair[0], "UTF-8");
	            String value = "";
	            if (pair.length > 1) {
	                value = URLDecoder.decode(pair[1], "UTF-8");
	            }
	            params.put(key, value);
        	}catch(Exception ex){}
        }
		return params;
	}
	
	public static String getRootURL(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		return StringUtils.substring(url, 0, url.lastIndexOf(uri));
	}
	
	public static Map<String, String> getParameterMap(HttpServletRequest request){
		HashMap<String, String> params = new HashMap<String, String>();
		for(@SuppressWarnings("unchecked")
		Enumeration<String> en = request.getParameterNames(); en.hasMoreElements();){
			String name = en.nextElement();
			params.put(name, request.getParameter(name));
		}
		return params;
	}
	
	public static String getCookieValue(HttpServletRequest currentRequest, String name) {
		String qc005 = null;
		Cookie cookie = RequestHelper.getCookie(currentRequest, name);
		if(cookie == null){
			Object qc005Obj = currentRequest.getAttribute(name);
			if(qc005Obj != null && qc005Obj instanceof String){
				qc005 = (String)qc005Obj;
			}
		}else{
			qc005 = cookie.getValue();
		}
		return qc005;
	}
	
	public static String xssEncode(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '\'':
				sb.append("&prime;");// &acute;");
				break;
			case '′':
				sb.append("&prime;");// &acute;");
				break;
			case '\"':
				sb.append("&quot;");
				break;
			case '＂':
				sb.append("&quot;");
				break;
			case '&':
				sb.append("＆");
				break;
			case '#':
				sb.append("＃");
				break;
			case '\\':
				sb.append('￥');
				break;
			case '=':
				sb.append("&#61;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
}
