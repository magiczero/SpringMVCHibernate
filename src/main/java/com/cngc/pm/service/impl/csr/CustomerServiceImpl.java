package com.cngc.pm.service.impl.csr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.csr.CustomerDAO;
import com.cngc.pm.model.csr.Customer;
import com.cngc.pm.service.csr.CustomerService;
import com.googlecode.genericdao.search.Search;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	@Transactional
	public void save(Customer customer) {
		// TODO 自动生成的方法存根
		customerDao.save(customer);
	}

	@Override
	public Customer getById(Long id) {
		// TODO 自动生成的方法存根
		return customerDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		return customerDao.removeById(id);
	}

	@Override
	public List<Customer> getAll() {
		// TODO 自动生成的方法存根
		return customerDao.findAll();
	}

	@Override
	public List<Customer> getByName(String name) {
		// TODO 自动生成的方法存根
		Search search = new Search(Customer.class);
		search.addFilterLike("name", name);
		return customerDao.search(search);
	}

}
