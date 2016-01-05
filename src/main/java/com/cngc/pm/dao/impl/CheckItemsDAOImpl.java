package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.CheckItemsDAO;
import com.cngc.pm.model.CheckItems;

@Repository
public class CheckItemsDAOImpl extends BaseDAOImpl<CheckItems, Long> implements
		CheckItemsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getItems(String code) {
//		String sql = "select * from styles";
//		this.getSession().createSQLQuery(sql).list();
		// TODO Auto-generated method stub
		//String hql = "from Style s1 inner join Style s2 on s1.style.id=s2.id inner join Style s3 on s2.style.id=s3.id inner join Style s4 on s3.style.id=s4.id inner join Style s5 on s4.style.id=s5.id left join CheckItems as items on items.item.id=s1.id  where  s5.code =:codes order by s4.order asc, s3.order asc, s2.order asc, s1.order asc";
		
		//String hql = "from Style as s1 inner join s1.style as s2  left join CheckItems as items on items.item.id=s1.id  where  s2.code =:codes order by s2.order asc, s1.order asc";
		String hql = "";
		if(code.equals("BMB22")) {
			hql = "from Style as s1 inner join s1.style as s2 inner join s2.style as s3 inner join s3.style as s4 inner join s4.style as s5 left join s1.items  where  s5.code =:codes order by s4.order asc, s3.order asc, s2.order asc, s1.order asc";
		} else
			hql = "from Style as s1 inner join s1.style as s2 inner join s2.style as s3 inner join s3.style as s4  left join s1.items  where  s4.code =:codes order by s3.order asc, s2.order asc, s1.order asc";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("codes", code);
		return query.list();
	}

}
