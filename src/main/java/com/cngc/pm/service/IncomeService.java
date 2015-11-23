package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Income;

public interface IncomeService {
	
	void save(Income income);
	boolean delById(Long id);
	Income getById(Long id);
	List<Income> getAll();

}
