package com.ruby.customandroiddemo.utils;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
	
	// MD5的引入
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			// //System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	private static byte[] iv = {1,2,3,4,5,6,7,8};
	
	public static String encryptDES(String encryptString, String encryptKey) {
		try{
			IvParameterSpec zeroIv = new IvParameterSpec(encryptKey.substring(0, 8).getBytes());
			SecretKeySpec key = new SecretKeySpec(encryptKey.substring(0, 8).getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		 
			return Base64.encode(encryptedData);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
		
	}
	
	/**
	  *  加密内容 
	  * @param encryptString
	  * @param encryptKey
	  * @return
	  */
	 public static String encryptDES2(String encryptString, String encryptKey) {
	  try{
	   IvParameterSpec zeroIv = new IvParameterSpec(iv);  
	         SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");  
	         Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
	         cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);  
	         String tmp = Base64.encode(encryptString.getBytes("UTF-8"));  
	         byte[] encryptedData = cipher.doFinal(tmp.getBytes("UTF-8"));  
	         return Base64.encode(encryptedData); 
	   
	  } catch (Exception e) {
	   e.printStackTrace();
	  } 
	  return "";
	 }
	
	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		byte[] byteMi = Base64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}
}
