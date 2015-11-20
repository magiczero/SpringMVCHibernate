package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.model.ModuleType;
import com.cngc.pm.model.Moudle;

@Repository
public class MoudleDAOImpl extends BaseDAOImpl<Moudle, Long> implements
		MoudleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Moudle> getAllMenus() {
		// TODO Auto-generated method stub
		String hql = "from Moudle m where m.type=:type";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("type", ModuleType.menu);
		
		return query.list();
	}

	@Override
	public Moudle update(Moudle entity) {
		// TODO Auto-generated method stub
		return super._merge(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Moudle> getAllByLevel() {
		// TODO Auto-generated method stub
		String hql = "from Moudle m where m.level=:level";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("level", 2);
		
		return query.list();
	}
}
