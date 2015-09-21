package com.cngc.pm.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Style;

@Repository
public class StyleDAOImpl extends BaseDAOImpl<Style, Long>  implements StyleDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Style> getListByType(int type) {
		String hql = "from Style s where s.style.id = :styleid ";
		Query query = this.getSession().createQuery(hql);
		 
		//query.setInteger(0, type);
		query.setParameter("styleid", Long.valueOf(type));
		 
		List<Style> list = query.list();
		
		if(list == null)
			return Collections.emptyList();
		
		return list;
	}

}
