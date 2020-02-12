package com.laimilife.business.goods.dao;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsImg;

public interface TbBusinessGoodsImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessGoodsImg record);

    int insertSelective(TbBusinessGoodsImg record);

    TbBusinessGoodsImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessGoodsImg record);

    int updateByPrimaryKey(TbBusinessGoodsImg record);
    
    void batchInsert(List<TbBusinessGoodsImg> imgList);
}