package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.NetworkCard;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface NetWorkDAO extends GenericDAO<NetworkCard, Long> {
	List<NetworkCard> getListByServerid(Long id);
}
