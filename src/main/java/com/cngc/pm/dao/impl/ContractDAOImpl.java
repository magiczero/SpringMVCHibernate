package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ContractDAO;
import com.cngc.pm.model.Contract;

@Repository
public class ContractDAOImpl extends BaseDAOImpl<Contract,Long> implements ContractDAO {

}
