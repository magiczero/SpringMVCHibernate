package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AccountType;

public interface AccountService {
	void save(Account account);
	boolean delById(Long id);
	Account getById(Long id);
	List<Account> getAll();
	/**
	 * 根据主类型获取子类型
	 * @param at
	 * @return
	 */
	List<Account> getListByType(AccountType at);
	Account getByCiCategoryCode(String categoryCode);
}
