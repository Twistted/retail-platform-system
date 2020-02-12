package com.laimilife.business.goods.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.base.exception.BaseException;
import com.laimilife.base.util.AssertUtil;
import com.laimilife.base.util.ConfigUtil;
import com.laimilife.base.util.StringUtil;
import com.laimilife.business.goods.dao.TbBusinessGoodsCharacteristicMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsImgMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsPropertyMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsSaleDayMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsSaleTimeMapper;
import com.laimilife.business.goods.dao.TbBusinessGoodsSpecificationMapper;
import com.laimilife.business.goods.dao.TestMapper;
import com.laimilife.business.goods.model.TbBusinessGoods;
import com.laimilife.business.goods.model.TbBusinessGoodsCharacteristic;
import com.laimilife.business.goods.model.TbBusinessGoodsImg;
import com.laimilife.business.goods.model.TbBusinessGoodsProperty;
import com.laimilife.business.goods.model.TbBusinessGoodsSaleDay;
import com.laimilife.business.goods.model.TbBusinessGoodsSaleTime;
import com.laimilife.business.goods.model.TbBusinessGoodsSpecification;
import com.laimilife.business.goods.model.Test;
import com.laimilife.business.goods.service.IGoodService;
import com.laimilife.business.goods.vo.GoodsVO;
import com.laimilife.frame.account.vo.TbBaseUser;

import net.coobird.thumbnailator.Thumbnails;

@Service("goodService")
public class GoodsServiceImpl implements IGoodService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TbBusinessGoodsMapper goodsMapper;
    @Autowired
    private TbBusinessGoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    private TbBusinessGoodsImgMapper goodsImgMapper;
    @Autowired
    private TbBusinessGoodsCharacteristicMapper goodsCharacMapper;
    @Autowired
    private TbBusinessGoodsPropertyMapper goodsPropertyMapper;
    @Autowired
    private TbBusinessGoodsSaleDayMapper goodsSaleDayMapper;
    @Autowired
    private TbBusinessGoodsSaleTimeMapper goodsSaleTimeMapper;
    
    public String insertTest() throws BaseException {
        Test test = new Test();
        test.setId(StringUtil.getUUID32());
        
        int insert = testMapper.insert(test);
        System.out.println(insert);
        if(1==1) {
            test.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            
            testMapper.insert(test);
            throw new BaseException("11","222");
        }
        return "1";
    }

    public List<Test> testPage(Map<String, String> searchValue) {
        return testMapper.testPage(searchValue);
    }

    /**
     * <p>Title:新增商品 </p>
     * <p>Description: </p>
     * @author zhangweiqiang
     * @throws IOException 
     * @createTime 2018-04-12 15:46:33
     */
    public void addGoods(GoodsVO goods, TbBaseUser user) throws BaseException, IOException {

    	Date nowDate = new Date();
    	// 补全字段
    	TbBusinessGoods tbGoods = new TbBusinessGoods();
    	BeanUtils.copyProperties(goods, tbGoods);
    	tbGoods.setId(StringUtil.getUUID32());
    	tbGoods.setCreateId(user.getId());
    	tbGoods.setCreateName(user.getUserName());
    	tbGoods.setCreateTime(nowDate);
    	/** 规格 */
    	List<TbBusinessGoodsSpecification> specificationList = goods.getSpecificationList();
    	Long minPrice = specificationList.get(0).getPrice();
    	for(TbBusinessGoodsSpecification specification : specificationList) {
    		specification.getPrice();
    		// 计算商品最低价
    		if(minPrice > specification.getPrice()) {
    			minPrice = specification.getPrice();
    		}
    		// 补全字段
    		specification.setId(StringUtil.getUUID32());
    		specification.setGoodsId(tbGoods.getId());
    		specification.setCreateId(user.getId());
    		specification.setCreateTime(nowDate);
    		specification.setCreateName(user.getUserName());
    		specification.setStatus("ON");
    	}
    	tbGoods.setMinPrice(minPrice);
    	/** 图片 */
    	List<TbBusinessGoodsImg> imgList = goods.getImgList();
    	String imgPath = ConfigUtil.getConfig("uploadFilePath") + imgList.get(0).getPath();
    	String[] imgPaths = imgPath.split(".");
        // 压缩第一张图片
        Thumbnails.of(imgPath).size(250, 250).toFile(imgPaths[0] + "_min." + imgPaths[1]);
        String[] imgUrls = imgList.get(0).getImgUrl().split(".");
        tbGoods.setImgUrl(imgUrls[0] + "_min." + imgUrls[1]);
        for(TbBusinessGoodsImg img : imgList) {
        	img.setGoodsId(tbGoods.getId());
        	img.setCreateId(user.getId());
        	img.setCreateName(user.getUserName());
        	img.setCreateTime(nowDate);
        }
        /** 特色 */
        List<String> strCharaList = goods.getCharaList();
        List<TbBusinessGoodsCharacteristic> charaList = new ArrayList<TbBusinessGoodsCharacteristic>();
        for(String strChara : strCharaList) {
        	TbBusinessGoodsCharacteristic chara = new TbBusinessGoodsCharacteristic();
        	chara.setCharName(strChara);
        	chara.setId(StringUtil.getUUID32());
        	chara.setGoodsId(tbGoods.getId());
        	chara.setCreateId(user.getId());
        	chara.setCreateName(user.getUserName());
        	chara.setCreateTime(nowDate);
        	charaList.add(chara);
        }
        /** 属性 */
        List<TbBusinessGoodsProperty> propertyList = goods.getPropertyList();
        for(TbBusinessGoodsProperty property : propertyList) {
        	property.setId(StringUtil.getUUID32());
        	property.setGoodsId(tbGoods.getId());
        	property.setCreateId(user.getId());
        	property.setCreateName(user.getUserName());
        	property.setCreateTime(nowDate);
        }
        /** 可售日期 */
        List<TbBusinessGoodsSaleDay> saleDayList = goods.getSaleDayList();
        if(!AssertUtil.isNull(saleDayList) && saleDayList.size() > 0) {
	        for(TbBusinessGoodsSaleDay day : saleDayList) {
	        	day.setId(StringUtil.getUUID32());
	        	day.setGoodsId(tbGoods.getId());
	        	day.setCreateId(user.getId());
	        	day.setCreateName(user.getUserName());
	        	day.setCreateTime(nowDate);
	        }
	        /** 批量插入 */
	        goodsSaleDayMapper.batchInsert(saleDayList);
	        
        }
        /** 可售时间 */
        List<TbBusinessGoodsSaleTime> saleTimeList = goods.getSaleTimeList();
        if(!AssertUtil.isNull(saleTimeList) && saleTimeList.size() > 0) {
	        for(TbBusinessGoodsSaleTime time : saleTimeList) {
	        	time.setId(StringUtil.getUUID32());
	        	time.setGoodsId(tbGoods.getId());
	        	time.setCreateId(user.getId());
	        	time.setCreateName(user.getUserName());
	        	time.setCreateTime(nowDate);
	        }
	        /** 批量插入 */
	        goodsSaleTimeMapper.batchInsert(saleTimeList);
        }
        /** 插入数据 */
        goodsMapper.insert(tbGoods);
        goodsSpecificationMapper.batchInsert(specificationList);
        goodsImgMapper.batchInsert(imgList);
        goodsCharacMapper.batchInsert(charaList);
        goodsPropertyMapper.batchInsert(propertyList);
    }
}
