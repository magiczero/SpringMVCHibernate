package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Contract;

public interface ContractService {

	void save(Contract contract);
	boolean delById(Long id);
	Contract getById(Long id);
	List<Contract> getAll();
}
