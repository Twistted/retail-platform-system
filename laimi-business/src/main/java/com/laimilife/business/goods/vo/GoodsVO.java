package com.laimilife.business.goods.vo;

import java.util.List;

import com.laimilife.business.goods.model.TbBusinessGoodsImg;
import com.laimilife.business.goods.model.TbBusinessGoodsProperty;
import com.laimilife.business.goods.model.TbBusinessGoodsSaleDay;
import com.laimilife.business.goods.model.TbBusinessGoodsSaleTime;
import com.laimilife.business.goods.model.TbBusinessGoodsSpecification;

public class GoodsVO {
	private String id; // 商品ID

    private String goodsName; // 商品名称

    private String assemblyType; // 商品组合类型：SINGLE单个商品，ASSEMBLY组合商品

    private String goodsBigTypeId; // 商品大类ID

    private String goodsSmallTypeId; // 商品小类ID

    private String status; // 状态 ON上架，OFF下架

    private String goodDesc; // 商品描述

    private String goodsUnit; // 商品单位

    private Long minPrice; // 商品规格中的最低价

    private String imgUrl; // 商品第一个图片的压缩图片路径
    
    // 以下是非数据库字段
    private List<TbBusinessGoodsImg> imgList; // 商品图片
    
    private List<TbBusinessGoodsSpecification> specificationList; // 商品规格
    
    private List<String> charaList; // 商品特色
    
    private List<TbBusinessGoodsProperty> propertyList; // 商品属性
    
    private List<TbBusinessGoodsSaleDay> saleDayList; // 可售日期
    
    private List<TbBusinessGoodsSaleTime> saleTimeList; // 可售时间段

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAssemblyType() {
        return assemblyType;
    }

    public void setAssemblyType(String assemblyType) {
        this.assemblyType = assemblyType;
    }

    public String getGoodsBigTypeId() {
        return goodsBigTypeId;
    }

    public void setGoodsBigTypeId(String goodsBigTypeId) {
        this.goodsBigTypeId = goodsBigTypeId;
    }

    public String getGoodsSmallTypeId() {
        return goodsSmallTypeId;
    }

    public void setGoodsSmallTypeId(String goodsSmallTypeId) {
        this.goodsSmallTypeId = goodsSmallTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

	public List<TbBusinessGoodsImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<TbBusinessGoodsImg> imgList) {
		this.imgList = imgList;
	}

	public List<TbBusinessGoodsSpecification> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<TbBusinessGoodsSpecification> specificationList) {
		this.specificationList = specificationList;
	}

	public List<String> getCharaList() {
		return charaList;
	}

	public void setCharaList(List<String> charaList) {
		this.charaList = charaList;
	}

	public List<TbBusinessGoodsProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<TbBusinessGoodsProperty> propertyList) {
		this.propertyList = propertyList;
	}

	public List<TbBusinessGoodsSaleDay> getSaleDayList() {
		return saleDayList;
	}

	public void setSaleDayList(List<TbBusinessGoodsSaleDay> saleDayList) {
		this.saleDayList = saleDayList;
	}

	public List<TbBusinessGoodsSaleTime> getSaleTimeList() {
		return saleTimeList;
	}

	public void setSaleTimeList(List<TbBusinessGoodsSaleTime> saleTimeList) {
		this.saleTimeList = saleTimeList;
	}
	
}
