package com.laimilife.base.util;

import com.laimilife.frame.config.service.ConfigService;

public final class ConfigUtil {

	private static ConfigService configService = (ConfigService) SpringBeanUtil.getBean("configService");
	
	public static String getConfig(String key) {
		return configService.searchByName(key);
	}
	
}
