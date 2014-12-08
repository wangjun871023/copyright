package com.copyright.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class Md5Util {

	/**
	 * Md5.
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String md5(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(value.getBytes());
			return toHex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}

	/**
	 * Md5.
	 * 
	 * @param value
	 *            the value
	 * @param int the value of
	 * @return the string
	 */
	public static String md5(String value, int bit) {
		if(bit==16){
			return md5(value).substring(8, 24).toString();
		}else{
			return md5(value);
		}
	}

	/**
	 * Md5.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	public static String md5(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(bytes);
			return toHex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * To hex.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	private static String toHex(byte bytes[]) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = Integer.toHexString(bytes[n] & 0xff);
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}

	public static void main(String[] args) {
		System.out.println(md5("admin123",32));
	}
}