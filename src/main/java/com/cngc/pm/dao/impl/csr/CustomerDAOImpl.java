package com.cngc.pm.dao.impl.csr;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.csr.CustomerDAO;
import com.cngc.pm.model.csr.Customer;
import com.cngc.pm.dao.impl.BaseDAOImpl;

@Repository
public class CustomerDAOImpl extends BaseDAOImpl<Customer,Long> implements CustomerDAO {


}
