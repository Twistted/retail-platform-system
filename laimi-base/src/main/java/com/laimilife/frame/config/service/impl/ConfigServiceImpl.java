package com.laimilife.frame.config.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.base.util.AssertUtil;
import com.laimilife.frame.config.dao.TbSysConfigMapper;
import com.laimilife.frame.config.model.TbSysConfig;
import com.laimilife.frame.config.service.ConfigService;


@Service("configService") 
public class ConfigServiceImpl implements ConfigService {
	
    @Autowired
    private TbSysConfigMapper configMapper;

	public String searchByName(String name) {
		TbSysConfig config = configMapper.searchByName(name);
		if(AssertUtil.isNull(config)) {
			return "";
		}
		return config.getValue();
	}
    

}
