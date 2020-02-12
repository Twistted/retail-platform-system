package com.laimilife.business.goods.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.laimilife.base.controller.BaseController;
import com.laimilife.base.exception.BaseException;
import com.laimilife.base.vo.Result;
import com.laimilife.business.goods.model.Test;
import com.laimilife.business.goods.service.IGoodService;
import com.laimilife.business.goods.vo.GoodsVO;
import com.laimilife.frame.account.vo.TbBaseUser;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    private final static transient Logger logger = LoggerFactory.getLogger(GoodsController.class);
    
    @Autowired
    private IGoodService goodService;
    
    @RequestMapping("/addGoods")
    public @ResponseBody Result addGoods(@RequestBody GoodsVO goods, HttpSession session) throws BaseException, IOException {
        TbBaseUser user = (TbBaseUser) session.getAttribute("user");
    	goodService.addGoods(goods, user);
        return setControllerResult("0", "新增成功");
    }
    

    @RequestMapping("/insertTest")
    public @ResponseBody Result insertTest() throws BaseException {
        goodService.insertTest();
        return setControllerResult("0", "新增成功");
    }
    
    @RequestMapping("testPage")
    public @ResponseBody Result testPage(@RequestBody Map<String,String> searchValue) throws BaseException {
        Page<Object> page = super.prePage(searchValue);
        List<Test> list = goodService.testPage(searchValue);
        return super.setPager(list, page);
    }
    
}
