package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsProperty;

public interface TbBusinessGoodsPropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsProperty record);

    int insertSelective(TbBusinessGoodsProperty record);

    TbBusinessGoodsProperty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsProperty record);

    int updateByPrimaryKey(TbBusinessGoodsProperty record);

	void batchInsert(List<TbBusinessGoodsProperty> propertyList);
}