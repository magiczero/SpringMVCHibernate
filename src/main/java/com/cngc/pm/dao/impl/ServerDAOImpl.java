package com.cngc.pm.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ServerDAO;
import com.cngc.pm.model.Servers;

@Repository
public class ServerDAOImpl extends BaseDAOImpl<Servers, Long> implements ServerDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Servers> getList() {
		String hql = "from Servers";
		Query query = this.getSession().createQuery(hql);
		List<Servers> list = query.list();
		
		if(list == null)
			return Collections.emptyList();
		
		return list;
	}

}
