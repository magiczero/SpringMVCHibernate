package com.cngc.pm.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.NetWorkDAO;
import com.cngc.pm.model.NetworkCard;

@Repository
public class NetCardDAOImpl extends BaseDAOImpl<NetworkCard, Long> implements
		NetWorkDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<NetworkCard> getListByServerid(Long id) {
		// TODO Auto-generated method stub
		String hql = "from NetworkCard nc where nc.server.id =:serverid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("serverid", id);
		
		List<NetworkCard> list = query.list();
		
		if(list.size() == 0)
			return Collections.emptyList();
		return list;
	}

}
