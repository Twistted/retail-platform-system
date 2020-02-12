package com.laimilife.business.goods.dao;

import com.laimilife.business.goods.model.TbBusinessGoodsStock;

public interface TbBusinessGoodsStockMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsStock record);

    int insertSelective(TbBusinessGoodsStock record);

    TbBusinessGoodsStock selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsStock record);

    int updateByPrimaryKey(TbBusinessGoodsStock record);
}