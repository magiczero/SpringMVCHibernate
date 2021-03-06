package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Manufacturer;

public interface ManufaService {

	/**
	 * 获取所有厂商列表
	 * @return
	 */
	List<Manufacturer> getList();

	void save(Manufacturer manufa);

	boolean isExistsNum(String num);

	Manufacturer getById(long id);

	boolean isExistsNumSelf(String num);

	void delete(Long id);
	
	boolean deleteIds(String ids);
}
