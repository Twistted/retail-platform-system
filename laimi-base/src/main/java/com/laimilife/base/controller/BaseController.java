package com.laimilife.base.controller;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.laimilife.base.util.AssertUtil;
import com.laimilife.base.util.StringUtil;
import com.laimilife.base.vo.Pager;
import com.laimilife.base.vo.Result;

public class BaseController {

    public Result setControllerResult(String code, String desc) {
        Result result = new Result(code, desc);
        return result;
    }
    
    public Result setControllerResult(String code, String desc, Object obj) {
        Result result = new Result(code, desc, obj);
        return result;
    }
    
    public Page<Object> prePage(Map<String, String> searchValue) {
        Page<Object> page = null;
        int currPage = 1;
        int pageSize = 10;
        if(!AssertUtil.isNull(searchValue)) {
            String strCurrPage = searchValue.get("currPage");
            if(!StringUtil.isNullEmpty(strCurrPage)) {
                currPage = Integer.valueOf(strCurrPage);
            }
            String strPageSize = searchValue.get("pageSize");
            if(!StringUtil.isNullEmpty(strPageSize)) {
                pageSize = Integer.valueOf(strPageSize);
            }
        }
        page = PageHelper.startPage(currPage, pageSize);
        return page;
    }
    
    public Page<Object> prePage(String strCurrPage, String strPageSize) {
        Page<Object> page = null;
        int currPage = 1;
        int pageSize = 10;
        if(!StringUtil.isNullEmpty(strCurrPage)) {
            currPage = Integer.valueOf(strCurrPage);
        }
        if(!StringUtil.isNullEmpty(strPageSize)) {
            pageSize = Integer.valueOf(strPageSize);
        }
        page = PageHelper.startPage(currPage, pageSize);
        return page;
    }
    
    public Page<Object> prePage(int currPage, int pageSize) {
        Page<Object> page = null;
        page = PageHelper.startPage(currPage, pageSize);
        return page;
    }
    
    public Result setPager(List list, Page<Object> page) {
        Pager pager = new Pager();
        pager.setCurrPage(page.getPageNum());
        pager.setPageSize(page.getPageSize());
        pager.setTotalRows(page.getTotal());
        pager.setMaxPage(page.getPages());
        pager.setData(list);
        Result result = new Result("0", "查询成功", pager);
        return result;
    }
    
}
