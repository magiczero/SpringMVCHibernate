package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.CheckItems;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface CheckItemsDAO extends GenericDAO<CheckItems, Long> {

	List<Object[]> getItems(String code);
}
