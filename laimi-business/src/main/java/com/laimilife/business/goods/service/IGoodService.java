package com.laimilife.business.goods.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.laimilife.base.exception.BaseException;
import com.laimilife.business.goods.model.Test;
import com.laimilife.business.goods.vo.GoodsVO;
import com.laimilife.frame.account.vo.TbBaseUser;

/**
 * <p>Title: 商品相关service接口</p>
 * <p>Description: </p>
 * @author zhangweiqiang
 * @createTime 2018-04-12 15:49:37
 */
public interface IGoodService {

    String insertTest() throws BaseException;

    List<Test> testPage(Map<String, String> searchValue);

    /**
     * <p>Title:新增商品 </p>
     * <p>Description: </p>
     * @author zhangweiqiang
     * @throws BaseException 
     * @throws IOException 
     * @createTime 2018-04-12 15:46:33
     */
    void addGoods(GoodsVO goods, TbBaseUser user) throws BaseException, IOException;
}
