package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.Account;

public interface AccountService {
	void save(Account account);
	boolean delById(Long id);
	Account getById(Long id);
	List<Account> getAll();
}
