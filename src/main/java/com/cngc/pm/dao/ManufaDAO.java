package com.cngc.pm.dao;

import java.util.Map;

import com.cngc.pm.model.Manufacturer;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ManufaDAO extends GenericDAO<Manufacturer, Long> {

	/**
	 * 判断num是否已经存在
	 * @param num
	 * @return
	 */
	boolean isExsitsNum(String num);

	Map<String, String> getAllMap();
}
