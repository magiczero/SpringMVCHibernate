package com.cngc.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.GroupDAO;
import com.cngc.pm.model.Group;
import com.googlecode.genericdao.search.Search;

@Repository
public class GroupDAOImpl extends BaseDAOImpl<Group, Long> implements
		GroupDAO {

	@Override
	public List<Group> getAllTopGroup() {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEmpty("parentGroup");
		search.addSortAsc("order");
		
		return this.search(search);
	}

}
