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

	/**
	 * 判断num是否已经存在出了自己
	 * @param num
	 * @return
	 */
	boolean isExsitsNumSelf(String num);
	
	/**
	 * 是否有服务器使用
	 * @param id
	 * @return
	 */
	boolean isServerUse(Long id);
}
