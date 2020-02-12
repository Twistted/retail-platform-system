package com.laimilife.frame.config.dao;

import com.laimilife.frame.config.model.TbSysConfig;

public interface TbSysConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysConfig record);

    int insertSelective(TbSysConfig record);

    TbSysConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysConfig record);

    int updateByPrimaryKey(TbSysConfig record);

    TbSysConfig searchByName(String name);
}