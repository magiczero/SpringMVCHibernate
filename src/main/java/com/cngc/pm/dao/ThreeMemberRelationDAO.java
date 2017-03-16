package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.Relations;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ThreeMemberRelationDAO extends GenericDAO<Relations, Long> {

	List<Relations> getItemsByType(ManageType type);
	
	List<Relations> getListByType(ManageType type);
}
