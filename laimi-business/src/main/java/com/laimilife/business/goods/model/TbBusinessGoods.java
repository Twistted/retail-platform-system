package com.laimilife.business.goods.model;

import java.util.Date;

public class TbBusinessGoods {
    private String id;

    private String goodsName;

    private String assemblyType;

    private String goodsBigTypeId;

    private String goodsSmallTypeId;

    private String status;

    private String goodDesc;

    private String goodsUnit;

    private Date createTime;

    private String createId;

    private String createName;

    private Date updateTime;

    private String updateId;

    private String updateName;

    private Long minPrice;

    private String imgUrl;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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
}