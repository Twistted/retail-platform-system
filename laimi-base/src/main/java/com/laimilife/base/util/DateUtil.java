package com.laimilife.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
    /**
     * Date对象格式化
     * @author: zhangweiqiang
     * @date: 2018年3月26日 下午6:35:59
     */
	public static String format(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	/**
	 * 反格式化成Date对象
	 * @author: zhangweiqiang
	 * @date: 2018年3月26日 下午6:35:39
	 */
	public static Date parse(String date, String pattern) {
	    SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
	}
}
