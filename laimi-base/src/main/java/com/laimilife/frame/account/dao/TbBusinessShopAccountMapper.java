package com.laimilife.frame.account.dao;

import com.laimilife.frame.account.model.TbBusinessShopAccount;

public interface TbBusinessShopAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbBusinessShopAccount record);

    int insertSelective(TbBusinessShopAccount record);

    TbBusinessShopAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbBusinessShopAccount record);

    int updateByPrimaryKey(TbBusinessShopAccount record);

    /**
     * 查询用户by用户名
     * @author: zhangweiqiang
     * @date: 2018年4月10日 下午2:31:41
     */
    TbBusinessShopAccount searchAccountByAccount(String account);
}