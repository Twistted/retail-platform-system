package com.laimilife.frame.account.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laimilife.base.controller.BaseController;
import com.laimilife.base.util.AssertUtil;
import com.laimilife.base.vo.Result;
import com.laimilife.frame.account.model.TbBusinessShopAccount;
import com.laimilife.frame.account.service.AccountService;
import com.laimilife.frame.account.vo.TbBaseUser;

@Controller
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public @ResponseBody Result login(@RequestParam String account, @RequestParam String password, HttpSession session) {
        TbBusinessShopAccount shopAccount = accountService.getUserByUsernameAndPassword(account, password);
        if(AssertUtil.isNull(account)) {
            return setControllerResult("10001", "用户名或密码错误");
        }
        TbBaseUser user = new TbBaseUser();
        user.setId(shopAccount.getId());
        user.setRoleId(shopAccount.getRoleId());
        user.setShopId(shopAccount.getShopId());
        user.setUserName(shopAccount.getUserName());
        user.setAccount(shopAccount.getAccount());
        session.setAttribute("user", user);
        return setControllerResult("0", "登陆成功");
    }
    
    @RequestMapping("/logout")
    public @ResponseBody Result logout(HttpSession session) {
        session.invalidate();
        return setControllerResult("0", "退出成功");
    }
    
}
