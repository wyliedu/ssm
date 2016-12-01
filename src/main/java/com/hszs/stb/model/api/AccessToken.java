package com.hszs.stb.model.api;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hszs.stb.common.helper.CodecHelper;
import com.hszs.stb.common.util.DateHelper;
import com.hszs.stb.common.util.IPHelper;

public class AccessToken {

	private static final byte VERSION = 0;
	private static final byte[] AESKEY = "e%Qiif&23Ugaos".getBytes();
	private static final byte[] IV = "ABCDEFGHIJKAA123".getBytes();

	public byte version;
	public int uid;
	public int device_checksum;
	public int ip;
	public int ts;
	public int authcode;
	public int passwdChecksum;
	public byte apptype;

	
	public static AccessToken of(UserInfo sui, String device_id, String ip,byte apptype) {
		AccessToken atoken = new AccessToken();
		atoken.version = VERSION;
		atoken.uid = sui.uid;
		atoken.device_checksum = crc32(device_id);
		atoken.ip = IPHelper.ip2int(ip);
		atoken.ts = DateHelper.currentTimeSeconds();
		atoken.authcode = sui.authcode;
		//atoken.passwdChecksum = crc32(sui.passwd);
		atoken.apptype = apptype;
		return atoken;
	}

	public String toToken() {
		ByteBuffer content = ByteBuffer.allocate(32);
		content.put(version);
		content.putInt(uid);
		content.putInt(device_checksum);
		content.putInt(ip);
		content.putInt(authcode);
		content.putInt(ts);
		//content.putInt(passwdChecksum);
		content.put(apptype);
		short rand2bytes = (short)(authcode & 0x0FFFF);
		ByteBuffer key = ByteBuffer.allocate(AESKEY.length + 2);
		key.put(AESKEY);
		key.putShort(rand2bytes);
		
		final String randStr = Integer.toHexString(rand2bytes);
		final String encrypted = encryptAsBase61(content.array(), key.array());
		return new StringBuilder(encrypted.length() + 8).append(randStr.length()).append(randStr)
				.append(encrypted).toString();
	}
	
	
	public static int fromHexString(String hex) {
		int dval = (int) (Long.parseLong(hex, 16) & 0x0FFFFFFFFL);
		return dval;
	}
	
	public static int crc32(String content) {
		if(content == null) {
			return 0;
		}
		Checksum checksum = new CRC32();
		checksum.update(content.getBytes(), 0, content.length());
		return (int)(checksum.getValue() & 0x0FFFFFFFFL);
	}
	
	public static AccessToken decode(String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		int len = NumberUtils.toInt(token.substring(0, 1));
		int rand = fromHexString(token.substring(1, len + 1));
		short rand2bytes = (short)(rand & 0x0FFFF);
		
		ByteBuffer key = ByteBuffer.allocate(AESKEY.length + 2);
		key.put(AESKEY);
		key.putShort(rand2bytes);
		
		String encryptedStr = token.substring(len + 1);
		byte[] plaindata = decryptFromBase61(encryptedStr, key.array());
		if(plaindata == null || plaindata.length < 25) {
			return null;
		}
		
		ByteBuffer content = ByteBuffer.wrap(plaindata);
		
		AccessToken atoken = new AccessToken();
		atoken.version = content.get();
		atoken.uid = content.getInt();
		atoken.device_checksum = content.getInt();
		atoken.ip = content.getInt();
		atoken.authcode = content.getInt();
		atoken.ts = content.getInt();
		//atoken.passwdChecksum = content.getInt();
		atoken.apptype = content.get();
		return atoken;
	}

	
	public static byte[] decryptFromBase61(String encryptedStr, byte[] encryptionKey) {
		try {
			byte[] encrypted = Base64.decodeBase64(CodecHelper.base61ToBase64(encryptedStr));
			byte[] plaindata = decrypt(encrypted, encryptionKey);
			return plaindata;
		}catch(Exception ex) {
			return null;
		}
	}

	
	public static String encryptAsBase61(byte[] plainText, byte[] encryptionKey) {
		try {
			final byte[] encrypted = encrypt(plainText, encryptionKey);
			final String encryptedStr = new String(Base64.encodeBase64(encrypted), "UTF8");
			return CodecHelper.base64ToBase61(encryptedStr);
		} catch (Exception ex) {
			return null;
		}
	}

	public static byte[] encrypt(byte[] plainText, byte[] encryptionKey)
			throws Exception {
		if (plainText == null) {
			return null;
		}
		int module = plainText.length % 16;
		if (module != 0) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(plainText.length + 16
					- module);
			byteBuffer.put(plainText);
			plainText = byteBuffer.array();
		}
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV));
		return cipher.doFinal(plainText);
	}
	
	 public static byte[] decrypt(byte[] cipherText, byte[] encryptionKey) throws Exception{
		    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		    SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
		    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV));
		    return cipher.doFinal(cipherText);
	 }
}
