package com.cngc.pm.dao.impl.cms;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.AccountDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.cms.Account;

@Repository
public class AccountDAOImpl extends BaseDAOImpl<Account,Long> implements AccountDAO{

}
