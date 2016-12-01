package com.hszs.stb.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.math.NumberUtils;

public class IPHelper {
	
	public static int[] parseIPMask(String ipmask){
		String[] chunks = ipmask.split("/");
		if(chunks.length <=0 || chunks.length >2){
			return null;
		}
		int[] ret = new int[2];
		ret[0] = ip2int(chunks[0]);
		if(ret[0] == 0){
			return null;
		}
		if(chunks.length == 1){
			ret[1] = 32;
		}else{
			ret[1] = NumberUtils.toInt(chunks[1]);
		}
		return ret;
	}
	
	public static int ip2int(String ip) {
		InetAddress address;
		try {
			address = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			return 0;
		}
		// 在给定主机名的情况下确定主机的 IP 址。
		byte[] bytes = address.getAddress();// 返回此 InetAddress 对象的原始 IP 地址
		int a, b, c, d;
		a = 0x0FF & (bytes[0]);
		b = 0x0FF & (bytes[1]);
		c = 0x0FF & (bytes[2]);
		d = 0x0FF & (bytes[3]);
		int result = (a << 24) | (b << 16) | (c << 8) | d;
		return result;
	}

	public static long ip2long(String ip) {
		int ipNum = ip2int(ip);
		return 0x0FFFFFFFF & ipNum;
	}
	
	public static String long2ip(long ip){
		int a = (int)(0x0FF & (ip >> 24));
		int b = (int)(0x0FF & (ip >> 16));
		int c = (int)(0x0FF & (ip >> 8));
		int d = (int)(0x0FF & (ip >> 0));
		return new StringBuilder(20).append(a).append(".").append(b).append(".").append(c).append(".").append(d).toString();
	}
	
	public static String int2ip(int ip){
		int a = 0x0FF & (ip >> 24);
		int b = 0x0FF & (ip >> 16);
		int c = 0x0FF & (ip >> 8);
		int d = 0x0FF & (ip >> 0);
		return new StringBuilder(20).append(a).append(".").append(b).append(".").append(c).append(".").append(d).toString();
	}
	
	public static boolean isInternalIP(String ip){
		if(ip == null){
			return false;
		}
		if(ip.startsWith("10.") || ip.startsWith("192.168.") || ip.equals("127.0.0.1") || ip.equals("0.0.0.0")){
			return true;
		}
		return false;
	}
}
