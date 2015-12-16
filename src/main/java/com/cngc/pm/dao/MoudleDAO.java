package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface MoudleDAO extends GenericDAO<Moudle, Long> {

	List<Moudle> getAllMenus();

	Moudle update(Moudle entity);

	List<Moudle> getAllByLevel();
	
	List<Moudle> getAllByUser(SysUser user);
}
