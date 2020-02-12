package com.laimilife.business.goods.dao;

import com.laimilife.business.goods.model.TbBusinessGoods;

public interface TbBusinessGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoods record);

    int insertSelective(TbBusinessGoods record);

    TbBusinessGoods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoods record);

    int updateByPrimaryKey(TbBusinessGoods record);
}