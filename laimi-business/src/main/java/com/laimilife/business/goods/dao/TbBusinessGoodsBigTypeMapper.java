package com.laimilife.business.goods.dao;

import java.util.List;
import java.util.Map;

import com.laimilife.business.goods.model.TbBusinessGoodsBigType;

public interface TbBusinessGoodsBigTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsBigType record);

    int insertSelective(TbBusinessGoodsBigType record);

    TbBusinessGoodsBigType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsBigType record);

    int updateByPrimaryKey(TbBusinessGoodsBigType record);

    List<TbBusinessGoodsBigType> listGoodsBigType(Map<String, String> searchValue);
}