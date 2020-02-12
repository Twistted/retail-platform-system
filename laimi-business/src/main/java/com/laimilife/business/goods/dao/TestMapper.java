package com.laimilife.business.goods.dao;

import java.util.List;
import java.util.Map;

import com.laimilife.business.goods.model.Test;

public interface TestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    List<Test> testPage(Map<String, String> searchValue);
}