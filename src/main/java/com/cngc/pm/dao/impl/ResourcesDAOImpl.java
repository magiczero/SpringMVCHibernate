package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ResourcesDAO;
import com.cngc.pm.model.Resources;

@Repository
public class ResourcesDAOImpl extends BaseDAOImpl<Resources, Long> implements
		ResourcesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Resources> findAllWithOrder() {
		// TODO Auto-generated method stub
		String hql = "from Resources order by id desc";
		Query query = this.getSession().createQuery(hql);
		return  query.list();
	}

	@Override
	public Resources update(Resources re) {
		// TODO Auto-generated method stub
		return super._merge(re);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resources getByURL(String url) {
		// TODO Auto-generated method stub
		String hql = "from Resources r where r.path=:url";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("url", url);
		
		List<Resources> list = query.list();
		
		if(list.size() > 0)
			return list.get(0);
		
		return null;
	}

}
