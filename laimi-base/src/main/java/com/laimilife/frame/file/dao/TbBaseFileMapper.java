package com.laimilife.frame.file.dao;

import com.laimilife.frame.file.model.TbBaseFile;

public interface TbBaseFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBaseFile record);

    int insertSelective(TbBaseFile record);

    TbBaseFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBaseFile record);

    int updateByPrimaryKey(TbBaseFile record);
}