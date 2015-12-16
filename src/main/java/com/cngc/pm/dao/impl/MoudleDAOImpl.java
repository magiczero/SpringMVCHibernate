package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.model.ModuleType;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.Search;

@Repository
public class MoudleDAOImpl extends BaseDAOImpl<Moudle, Long> implements
		MoudleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Moudle> getAllMenus() {
		// TODO Auto-generated method stub
		String hql = "from Moudle m where m.type=:type order by m.priority";
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
		String hql = "from Moudle m where m.level=:level order by m.priority";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("level", 2);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moudle> getAllByUser(SysUser user) {
		// TODO Auto-generated method stub
		Search search = new Search(Moudle.class);
		
		return this._search(search);
	}
}
