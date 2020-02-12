/**
 * 
 */
package com.laimilife.business.goods.service;

import java.util.List;
import java.util.Map;

import com.laimilife.business.goods.model.TbBusinessGoodsBigType;

/**
 * <p>Title: 商品分类相关service接口</p>
 * <p>Desciption: </p>
 * @author chenyaogeng
 * @createtime 2018年4月13日 上午7:13:31
 */
public interface IBigTypeService {
    List<TbBusinessGoodsBigType> listGoodsBigType(Map<String, String> searchValue);
}
