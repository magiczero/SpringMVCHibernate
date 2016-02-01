package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.AuthReso;
import com.cngc.pm.model.Authority;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface AuthResoDAO extends GenericDAO<AuthReso, Long> {

	/**
	 * 删除权限对应的资源
	 * @param authority
	 */
	void deleteByAuth(Authority authority);

	List<AuthReso> getListByAuth(Authority auth);
}
