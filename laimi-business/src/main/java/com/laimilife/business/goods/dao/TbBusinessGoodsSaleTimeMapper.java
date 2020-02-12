package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsSaleTime;

public interface TbBusinessGoodsSaleTimeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsSaleTime record);

    int insertSelective(TbBusinessGoodsSaleTime record);

    TbBusinessGoodsSaleTime selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsSaleTime record);

    int updateByPrimaryKey(TbBusinessGoodsSaleTime record);

	void batchInsert(List<TbBusinessGoodsSaleTime> saleTimeList);
}