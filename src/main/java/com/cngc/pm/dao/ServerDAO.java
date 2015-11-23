package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Servers;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ServerDAO extends GenericDAO<Servers, Long> {

	List<Servers> getList();
}
