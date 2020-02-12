package com.laimilife.business.goods.model;

import java.util.Date;

public class TbBusinessGoodsStockLog {
    private String id;

    private String shopId;

    private String goodsId;

    private String specificationId;

    private Integer changeStock;

    private Integer surplusStock;

    private Date createTime;

    private String createId;

    private String createName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
    }

    public Integer getChangeStock() {
        return changeStock;
    }

    public void setChangeStock(Integer changeStock) {
        this.changeStock = changeStock;
    }

    public Integer getSurplusStock() {
        return surplusStock;
    }

    public void setSurplusStock(Integer surplusStock) {
        this.surplusStock = surplusStock;
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
}