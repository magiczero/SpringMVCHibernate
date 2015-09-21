package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Style;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface StyleDAO extends GenericDAO<Style, Long> {

	/**
	 * 根据上级类型获得类型
	 * @param type  类型
	 * @return
	 */
	List<Style> getListByType(int type);
}
