package com.delicacy.oatmeal.common.util.security;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 32位MD5摘要算法帮助类
 *
 */
@Slf4j
public class Md5AlgorithmUtil {
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 生成摘要串
	 * @param content
	 * @param salt 盐值
	 * @return
	 */
	public static String sign(String content, String salt) {
		content = content.concat("&salt=").concat(salt);
		try {
			return md5Digest(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("系统错误", e);
		}
		return null;
	}
	
	/**
	 * 校验摘要串
	 * @param content 不包含sign的字符串
	 * @param sign
	 * @param salt 盐值
	 * @return
	 */
	public static boolean checkSign(String content, String sign, String salt) {
		content = content.concat("&salt=").concat(salt);
		try {
			return md5Digest(content.getBytes("UTF-8")).equals(sign);
		} catch (UnsupportedEncodingException e) {
			log.error("系统出错", e);
		}
		return false;
	}
	
	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 转换字节数组为高位字符串
	 * @param b 字节数组
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5 摘要计算(byte[]).
	 * @param src byte[]
	 * @throws Exception
	 * @return String
	 */
	private static String md5Digest(byte[] src) {
		MessageDigest alg;
		try {
			// MD5 is 32 bit message digest
			alg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("系统错误", e);
			return null;
		} 
		return byteArrayToHexString(alg.digest(src));
	}
}
