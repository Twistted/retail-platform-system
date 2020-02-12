package com.laimilife.business.goods.model;

import java.util.Date;

public class TbBusinessGoodsAssembly {
    private String id;

    private String goodsId;

    private String singleGoodsId;

    private Date createTime;

    private String createId;

    private String createName;

    private Long price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSingleGoodsId() {
        return singleGoodsId;
    }

    public void setSingleGoodsId(String singleGoodsId) {
        this.singleGoodsId = singleGoodsId;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}