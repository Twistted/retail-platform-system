package com.laimilife.business.goods.dao;

import com.laimilife.business.goods.model.TbBusinessGoodsAssembly;

public interface TbBusinessGoodsAssemblyMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsAssembly record);

    int insertSelective(TbBusinessGoodsAssembly record);

    TbBusinessGoodsAssembly selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsAssembly record);

    int updateByPrimaryKey(TbBusinessGoodsAssembly record);
}