package com.laimilife.base.util;

import java.util.UUID;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空或空串
	 * @author: zhangweiqiang
	 * @date: 2017年12月19日 下午10:25:25
	 */
	public static boolean isNullEmpty(String str) {
		if(null == str) {
			return true;
		}
		if(str.length() <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取32位的UUID
	 * @author: zhangweiqiang
	 * @date: 2017年12月19日 下午10:25:48
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
