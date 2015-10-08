package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.ServerSoftware;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ServerSoftDAO extends GenericDAO<ServerSoftware, Long> {

	List<ServerSoftware> getListByServer(Long serverid);
}
