package com.laimilife.business.goods.dao;

import com.laimilife.business.goods.model.TbBusinessGoodsSmallType;

public interface TbBusinessGoodsSmallTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsSmallType record);

    int insertSelective(TbBusinessGoodsSmallType record);

    TbBusinessGoodsSmallType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsSmallType record);

    int updateByPrimaryKey(TbBusinessGoodsSmallType record);
}