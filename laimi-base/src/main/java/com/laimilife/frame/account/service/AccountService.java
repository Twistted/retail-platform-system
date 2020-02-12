package com.laimilife.frame.account.service;

import com.laimilife.frame.account.model.TbBusinessShopAccount;

public interface AccountService {

	TbBusinessShopAccount getUserByUsernameAndPassword(String account, String password);
	
}
