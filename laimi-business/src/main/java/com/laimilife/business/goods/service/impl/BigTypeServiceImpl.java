/**
 * 
 */
package com.laimilife.business.goods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.business.goods.dao.TbBusinessGoodsBigTypeMapper;
import com.laimilife.business.goods.model.TbBusinessGoodsBigType;
import com.laimilife.business.goods.service.IBigTypeService;

/**
 * <p>Title: </p>
 * <p>Desciption: </p>
 * @author chenyaogeng
 * @createtime 2018年4月13日 上午7:16:46
 */
@Service("goodsBigTypeService")
public class BigTypeServiceImpl implements IBigTypeService {

    @Autowired
    private TbBusinessGoodsBigTypeMapper goodsBigTypeMapper;
    
    public List<TbBusinessGoodsBigType> listGoodsBigType(Map<String, String> searchValue) {
        // TODO Auto-generated method stub
        return goodsBigTypeMapper.listGoodsBigType(searchValue);
    }
    
    

}
