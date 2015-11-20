package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Authority;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface AuthorityDAO extends GenericDAO<Authority, Long> {

	List<Authority> getAlllWithOrder();

	void update(Authority authority);

}
