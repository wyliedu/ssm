package com.hszs.stb.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodecHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(CodecHelper.class);

	public static final String DEFAULT_KEY = "qiyi_K1DJ@lR%4k.";//]hksldd08eGdlqfj
	
	public static final String PHP_DEFAULT_KEY = "qiyi_K1DJ@lR%4k.]hksldd08eGdlqfj";
	public static final String PHP_DEFAULT_IV = "12345678";
	
	public static final String memKeyEncode(String key){
		return StringUtils.replace(key, " ", "%20");
	}
	
	public static final String urlencode(String value){
		if(StringUtils.isBlank(value)){
			return "";
		}
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Fail to encode string " + value + " with UTF-8");
			return value;
		}
		
	}
	
	public static final String md5(String input) {
		return DigestUtils.md5Hex(input);
	}
	
	public static String base64Encode(String input){
		return new String(Base64.encodeBase64(input.getBytes()));
	}

	public static final String encryptWithDefaultKey(String input) {
		return encrypt(input, DEFAULT_KEY);
	}
	
	public static final String decryptWithDefaultKey(String input) {
		return decrypt(input, DEFAULT_KEY);
	}
	
	public static final String encrypt(String input, String key) {
		return BlowfishEncryptor.getInstance(key).encrypt(input);
	}
	
	public static final String decrypt(String input, String key) {
		return BlowfishEncryptor.getInstance(key).decrypt(input);
	}
	
	public static final String phpEncrypt(String input) {
		return BlowfishEncryptor.getInstance(PHP_DEFAULT_KEY, PHP_DEFAULT_IV).encryptHex(input);
	}
	
	public static final String phpDecrypt(String input) {
		return BlowfishEncryptor.getInstance(PHP_DEFAULT_KEY, PHP_DEFAULT_IV).decryptHex(input);
	}
	
	public static String base64ToBase62(String input){
		input = input.replaceAll("m", "m1");
		input = input.replaceAll("\\+", "m2");
		input = input.replaceAll("/", "m3");
		return input;
	}
	
	public static String base64ToBase61(String input){
		input = input.replaceAll("m", "m1");
		input = input.replaceAll("\\+", "m2");
		input = input.replaceAll("/", "m3");
		input = input.replaceAll("=", "m4");
		return input;
	}
	
	public static String base62ToBase64(String input){
		input = input.replaceAll("m3", "/");
		input = input.replaceAll("m2", "+");
		input = input.replaceAll("m1", "m");
		return input;
	}
	
	public static String base61ToBase64(String input){
		input = input.replaceAll("m4", "=");
		input = input.replaceAll("m3", "/");
		input = input.replaceAll("m2", "+");
		input = input.replaceAll("m1", "m");
		return input;
	}
	
	public static String base61Encode(final byte[] data){
		String b64 = new String(Base64.encodeBase64(data));
		return base64ToBase61(b64);
	}
	
	public static byte[] base61Decode(final String input){
		String b64 = base61ToBase64(input);
		return Base64.decodeBase64(b64);
	}
	
	public static String optEncode(String input){
		input = encryptWithDefaultKey(input);
		input = base64ToBase62(input);
		return StringUtils.substringBefore(input, "=");
	}
	
	public static String optDecode(String input){
		input = base62ToBase64(input);
		input = decryptWithDefaultKey(input);
		return input;
	}
	
	public static class BlowfishEncryptor {
		private static final Logger logger = LoggerFactory.getLogger(BlowfishEncryptor.class);
		
	    private static final String CIPHER_NAME="Blowfish/CFB8/NoPadding";
	    private static final String KEY_SPEC_NAME="Blowfish";

	    private static final ThreadLocal<HashMap<String, BlowfishEncryptor>> pool=new ThreadLocal<HashMap<String,BlowfishEncryptor>>();

	    private Cipher enCipher;
	    private Cipher deCipher;

	    private String key;

	    private BlowfishEncryptor(String key){
	    	this(key, StringUtils.substring(DigestUtils.md5Hex(key), 0, 8));
	    }
	    
	    private BlowfishEncryptor(String key, String iv){
	        try {
	            this.key = key;
	            if(StringUtils.length(iv) != 8){
	            	iv = StringUtils.substring(DigestUtils.md5Hex(key), 0, 8);
	            }
	            SecretKeySpec secretKeySpec=new SecretKeySpec(key.getBytes(), KEY_SPEC_NAME);
	            IvParameterSpec ivParameterSpec=new IvParameterSpec(iv.getBytes());
	            enCipher=Cipher.getInstance(CIPHER_NAME);
	            deCipher=Cipher.getInstance(CIPHER_NAME);
	            enCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);
	            deCipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParameterSpec);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	logger.error("Blowfish初始化失败", e);
	        }

	    }

	    public static String encryptBlowfish(String s,String key){
	        return getInstance(key).encrypt(s);
	    }

	    public static String decryptBlowfish(String s,String key){
	        return getInstance(key).decrypt(s);
	    }

	    private static BlowfishEncryptor getInstance(String key){
	    	return getInstance(key, null);
	    }
	    
	    private static BlowfishEncryptor getInstance(String key, String iv){
	        HashMap<String, BlowfishEncryptor> keyMap = pool.get();
	        if(keyMap==null || keyMap.isEmpty()){
	            keyMap=new HashMap<String, BlowfishEncryptor>();
	            pool.set(keyMap);
	        }
	        final String cryptorKey = (iv == null ? key : key + iv); 
	        BlowfishEncryptor instance=keyMap.get(cryptorKey);
	        if(instance==null || !StringUtils.equals(instance.key, key)){
	            instance=new BlowfishEncryptor(key, iv);
	            keyMap.put(cryptorKey, instance);
	        }
	        return instance;
	    }
	    
	    /**
	     * 加密
	     * @param s
	     * @return
	     */
	    private String encrypt(String s){
	        String result=null;
	        if(StringUtils.isNotBlank(s)){
	            try {
	                byte[] encrypted=enCipher.doFinal(s.getBytes());
	                result=new String(Base64.encodeBase64(encrypted));
	            } catch (Exception e) {
	            	logger.error("Blowfish加密失败", e);
	            }
	        }
	        return result;
	    }
	    
	    /**
	     * 加密
	     * @param s
	     * @return
	     */
	    private String encryptHex(String s){
	        String result=null;
	        if(StringUtils.isNotBlank(s)){
	            try {
	                byte[] encrypted=enCipher.doFinal(s.getBytes());
	                result=new String(Hex.encodeHex(encrypted, true));
	            } catch (Exception e) {
	            	logger.error("Blowfish加密失败", e);
	            }
	        }
	        return result;
	    }
	    
	    /**
	     * 解密
	     * @param s
	     * @return
	     */
	    private String decrypt(String s){
	        String result=null;
	        if(StringUtils.isNotBlank(s)){
	            try {
	                byte[] decrypted=Base64.decodeBase64(s.getBytes());
	                result=new String(deCipher.doFinal(decrypted));
	            } catch (Exception e) {
	            	logger.error("Blowfish解密失败", e);
	                resetInstance();
	            }
	        }
	        return result;
	    }
	    
	    /**
	     * 解密
	     * @param s
	     * @return
	     */
	    private String decryptHex(String s){
	        String result=null;
	        if(StringUtils.isNotBlank(s)){
	            try {
	            	
	                byte[] decrypted=Hex.decodeHex(s.toCharArray());
	                result=new String(deCipher.doFinal(decrypted));
	            } catch (Exception e) {
	            	logger.error("Blowfish解密失败", e);
	                resetInstance();
	            }
	        }
	        return result;
	    }

	    private void resetInstance(){
	        pool.set(null);
	    }
	}
}
