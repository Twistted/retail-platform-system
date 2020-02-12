package com.laimilife.frame.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.base.util.AssertUtil;
import com.laimilife.frame.account.dao.TbBusinessShopAccountMapper;
import com.laimilife.frame.account.model.TbBusinessShopAccount;
import com.laimilife.frame.account.service.AccountService;

@Service("accountService") 
public class AccountServiceImpl implements AccountService {
    
    @Autowired
	private TbBusinessShopAccountMapper accountMapper;

	public TbBusinessShopAccount getUserByUsernameAndPassword(String account, String password) {
	    TbBusinessShopAccount user = accountMapper.searchAccountByAccount(account);
		if(!AssertUtil.isNull(user) && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
