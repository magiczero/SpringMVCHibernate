package com.cngc.pm.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.HardDiskDAO;
import com.cngc.pm.model.HardDisk;

@Repository
public class HardDiskDAOImpl extends BaseDAOImpl<HardDisk, Long> implements
		HardDiskDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<HardDisk> getList() {
		String hql = "from HardDisk";
		Query query = this.getSession().createQuery(hql);
		List<HardDisk> list = query.list();
		
		if(list.size() == 0)
			return Collections.emptyList();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HardDisk> getListByServer(int serverid) {
		// TODO Auto-generated method stub
		String hql = "from HardDisk hd where hd.server.id =:serverid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("serverid", Long.valueOf(serverid));
		
		List<HardDisk> list = query.list();
		
		if(list.size() == 0)
			return Collections.emptyList();
		
		return list;
	}

	
}
