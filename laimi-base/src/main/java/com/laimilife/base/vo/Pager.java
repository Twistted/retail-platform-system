package com.laimilife.base.vo;

import java.util.List;

public class Pager {

    private Integer currPage; // 当前分页
    
    private Integer pageSize; // 分页大小
    
    private Long totalRows; // 总共行数

    private Integer maxPage; // 最大页数
    
    private List<Object> data; // 分页内容

    public Pager() {
        
    }
    
    public Pager(Integer currPage, Integer pageSize, Long totalRows, Integer maxPage, List<Object> data) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalRows = totalRows; 
        this.maxPage = maxPage;
        this.data = data;
    }
    
    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
    
}
