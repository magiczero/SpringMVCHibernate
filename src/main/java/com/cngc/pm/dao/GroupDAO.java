package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Group;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface GroupDAO extends GenericDAO<Group, Long> {

	List<Group> getAllTopGroup();
}
