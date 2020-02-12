package com.laimilife.business.goods.dao;

import com.laimilife.business.goods.model.TbBusinessGoodsStockLog;

public interface TbBusinessGoodsStockLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsStockLog record);

    int insertSelective(TbBusinessGoodsStockLog record);

    TbBusinessGoodsStockLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsStockLog record);

    int updateByPrimaryKey(TbBusinessGoodsStockLog record);
}