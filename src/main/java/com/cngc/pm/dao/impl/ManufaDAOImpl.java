package com.cngc.pm.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ManufaDAO;
import com.cngc.pm.model.Manufacturer;

@Repository
public class ManufaDAOImpl extends BaseDAOImpl<Manufacturer, Long> implements ManufaDAO {

	@Override
	public boolean isExsitsNum(String num) {
		// TODO Auto-generated method stub
		String hql = "from Manufacturer m where m.num=:num";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("num", num);
		
		if(query.list().size() > 0) return true;
		
		return false;
	}

	@Override
	public Map<String, String> getAllMap() {
		// TODO Auto-generated method stub
		Map<String , String> maps = new HashMap<String, String>();
		
		for(Manufacturer manufa : findAll()) {
			maps.put(manufa.getId().toString(), manufa.getName());
		}
		
		return maps;
	}

}
