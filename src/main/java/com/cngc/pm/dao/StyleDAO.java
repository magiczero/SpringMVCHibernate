package com.cngc.pm.dao;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.Style;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface StyleDAO extends GenericDAO<Style, Long> {

	/**
	 * 根据上级类型获得类型
	 * @param type  类型
	 * @return
	 */
	List<Style> getListByType(int type);

	Set<Style> getSet(Long[] ids);
	
	Style getByCode(String code);
}
