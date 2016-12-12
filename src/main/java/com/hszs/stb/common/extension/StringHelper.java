package com.hszs.stb.common.extension;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * String 工具类
 * @author du
 *
 */
public class StringHelper {

	/**
	 * 将String转换为Integer类型的List
	 * @param str
	 * @param split
	 * @return
	 */
	public final static List<Integer> toIntegerList(String str, String split){
		List<Integer> ret=new ArrayList<Integer>();	
		if(str!=null && !str.isEmpty()){
			String[] strArray=str.split(split);
			
			for(String item : strArray){
				if(!item.isEmpty())
					ret.add(Integer.parseInt(item));
			}
		}
		return ret;	
	}
	
	/**
	 * MD5字符串加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public final static String md5(String str) throws NoSuchAlgorithmException {
        final char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        byte[] btInput = str.getBytes();
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest md5Inst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        md5Inst.update(btInput);
        // 获得密文
        byte[] bytes = md5Inst.digest();
        
        StringBuffer strResult=new StringBuffer();
        // 把密文转换成十六进制的字符串形式
        for(int i=0;i<bytes.length;i++){
			strResult.append(hexDigits[(bytes[i]>>4)&0x0f]);
			strResult.append(hexDigits[bytes[i]&0x0f]);
        }
        return strResult.toString();
    }
	
	/**
	 * SHA-1字符串加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public final static String sha1(String str) throws NoSuchAlgorithmException{
		final char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		byte[] btInput = str.getBytes();
		// 获得SHA-1摘要算法的 MessageDigest 对象
        MessageDigest sha1Inst = MessageDigest.getInstance("SHA-1");
        // 使用指定的字节更新摘要
        sha1Inst.update(btInput);
        // 获得密文
        byte[] bytes = sha1Inst.digest();
        
        StringBuffer strResult=new StringBuffer();
        // 把密文转换成十六进制的字符串形式
        for(int i=0;i<bytes.length;i++){
			strResult.append(hexDigits[(bytes[i]>>4)&0x0f]);
			strResult.append(hexDigits[bytes[i]&0x0f]);
        }
        return strResult.toString();
	}
	
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
	        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
	        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
	        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
	        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
	        '4', '5', '6', '7', '8', '9', '+', '/', };

	    private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	        -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
	        60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
	        -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
	        38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
	        -1, -1 };
	    
	    /**
	    * 解密
	    * @param str
	    * @return
	    */
	    public static String decode64(String str) {
	            byte[] data = str.getBytes();
	            int len = data.length;
	            ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
	            int i = 0;
	            int b1, b2, b3, b4;

	            while (i < len) {
	                    do {
	                            b1 = base64DecodeChars[data[i++]];
	                    } while (i < len && b1 == -1);
	                    if (b1 == -1) {
	                            break;
	                    }

	                    do {
	                            b2 = base64DecodeChars[data[i++]];
	                    } while (i < len && b2 == -1);
	                    if (b2 == -1) {
	                            break;
	                    }
	                    buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

	                    do {
	                            b3 = data[i++];
	                            if (b3 == 61) {
	                                    return new String(buf.toByteArray());
	                            }
	                            b3 = base64DecodeChars[b3];
	                    } while (i < len && b3 == -1);
	                    if (b3 == -1) {
	                            break;
	                    }
	                    buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

	                    do {
	                            b4 = data[i++];
	                            if (b4 == 61) {
	                                    return new String(buf.toByteArray());
	                            }
	                            b4 = base64DecodeChars[b4];
	                    } while (i < len && b4 == -1);
	                    if (b4 == -1) {
	                            break;
	                    }
	                    buf.write((int) (((b3 & 0x03) << 6) | b4));
	            }
	            return new String(buf.toByteArray());
	    }
}
