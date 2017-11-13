package com.cngc.pm.service.csr;

import java.util.List;
import com.cngc.pm.model.csr.Customer;

public interface CustomerService {

	void save(Customer customer);
	Customer getById(Long id);
	boolean delById(Long id);
	List<Customer> getAll();
	List<Customer> getByName(String name);
}
