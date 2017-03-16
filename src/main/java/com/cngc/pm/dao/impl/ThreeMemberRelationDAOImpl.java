package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ThreeMemberRelationDAO;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.Relations;
import com.googlecode.genericdao.search.Search;

@Repository
public class ThreeMemberRelationDAOImpl extends BaseDAOImpl<Relations, Long> implements ThreeMemberRelationDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Relations> getItemsByType(ManageType type) {
		// TODO Auto-generated method stub
		String hql = "from Relations  r where r.type =:type group by r.item";
		
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("type", type);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relations> getListByType(ManageType type) {
		// TODO Auto-generated method stub
		Search search = new Search(Relations.class);
		
		search.addFilterEqual("type", type);
		search.addSortAsc("item.id");
		search.addSortAsc("order");
		
		
		return this._search(search);
	}

}
