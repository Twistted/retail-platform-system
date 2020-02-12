package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsSpecification;

public interface TbBusinessGoodsSpecificationMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsSpecification record);

    int insertSelective(TbBusinessGoodsSpecification record);

    TbBusinessGoodsSpecification selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsSpecification record);

    int updateByPrimaryKey(TbBusinessGoodsSpecification record);
    
    void batchInsert(List<TbBusinessGoodsSpecification> specificationList);
}