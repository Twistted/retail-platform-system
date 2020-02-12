package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsCharacteristic;

public interface TbBusinessGoodsCharacteristicMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsCharacteristic record);

    int insertSelective(TbBusinessGoodsCharacteristic record);

    TbBusinessGoodsCharacteristic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsCharacteristic record);

    int updateByPrimaryKey(TbBusinessGoodsCharacteristic record);

	void batchInsert(List<TbBusinessGoodsCharacteristic> charaList);
}