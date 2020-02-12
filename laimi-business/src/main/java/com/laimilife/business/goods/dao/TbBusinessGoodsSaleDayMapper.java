package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsSaleDay;

public interface TbBusinessGoodsSaleDayMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsSaleDay record);

    int insertSelective(TbBusinessGoodsSaleDay record);

    TbBusinessGoodsSaleDay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsSaleDay record);

    int updateByPrimaryKey(TbBusinessGoodsSaleDay record);

	void batchInsert(List<TbBusinessGoodsSaleDay> saleDayList);
}