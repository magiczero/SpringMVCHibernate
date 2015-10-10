package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ServerSoftDAO;
import com.cngc.pm.model.ServerSoftware;
import com.cngc.pm.model.Servers;

@Repository
public class ServerSoftDAOImpl extends BaseDAOImpl<ServerSoftware, Long> implements
		ServerSoftDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ServerSoftware> getListByServer(Long serverid) {
		// TODO Auto-generated method stub
		String hql = "from ServerSoftware  s where s.server.id =:serverid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("serverid", serverid);
		
		return query.list();
	}

	@Override
	public boolean isServerSoftware(Long serverid, Long softwareid) {
		// TODO Auto-generated method stub
		String hql = "from ServerSoftware  s where s.server.id =:serverid and s.software.id =:softwareid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("serverid", serverid);
		query.setParameter("softwareid", softwareid);
		
		if(query.list().size() > 0) {
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Servers> getListBySoftware(long softwareid) {
		// TODO Auto-generated method stub
		String hql = "from ServerSoftware  s where s.software.id =:softwareid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("softwareid", softwareid);
		
		return query.list();
	}


}
