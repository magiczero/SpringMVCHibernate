package com.cngc.pm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.DocumentDAO;
import com.cngc.pm.model.DocAuth;
import com.cngc.pm.model.Document;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class DocumentDAOImpl extends BaseDAOImpl<Document, Long> implements DocumentDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getListBySelfAndOpen(Long userId) {
		// TODO Auto-generated method stub
		String hql = "from Document d where d.auth=:auth or d.user.id=:id order by d.auth";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("auth", DocAuth.open);
		query.setParameter("id", userId);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getListBySelfAndOpenAndStyle(Long userId, Long styleId) {
		// TODO Auto-generated method stub
//		String hql = "from Document as d left join d.styles as style with style.id =:styleid where d.auth=:auth or d.user.id=:id order by d.auth";
		String hql = "from Document as d left join d.styles as style  where (d.auth=:auth or d.user.id=:id) and style.id =:styleid order by d.auth";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("auth", DocAuth.open);
		query.setParameter("id", userId);
		query.setParameter("styleid", styleId);
		
		List<Object[]> list1 = query.list(); 
		
		List<Document> list = new ArrayList<>();
		for(Object[] obj : list1) {
			list.add((Document)obj[0]);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getListBySelf(Long userId) {
		// TODO Auto-generated method stub
		String hql = "from Document as d where  d.user.id=:id order by d.auth";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", userId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getListByLastVersion() {
		// TODO Auto-generated method stub
		String hql = "from Document as d where  d.last=:isLast";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("isLast", true);
		
		return query.list();
	}

	@Override
	public Document update(Document document) {
		// TODO Auto-generated method stub
		return super._merge(document);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getByStyle(long styleid) {
		// TODO Auto-generated method stub
		String hql = "from Document d where d.style.id=:styleid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("styleid", styleid);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getByItem(long itemid) {
		// TODO Auto-generated method stub
		String hql = "from Document as d left join d.checkItems as items  where  items.id =:itemid";
		Query query = this.getSession().createQuery(hql);
		
		query.setParameter("itemid", itemid);
		
		List<Object[]> list1 = query.list(); 
		
		List<Document> list = new ArrayList<>();
		for(Object[] obj : list1) {
			int size = obj.length;
			for(int i=0; i<size; i++) {
				if(obj[i] instanceof Document)
					list.add((Document)obj[i]);
			}
		}
		
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Document> getListWithPage(Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		return this.getSession().createCriteria(Document.class)
				.setFirstResult(offset==null?0:offset)
				.setMaxResults(maxResults==null?10:maxResults)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchResult<Document> getAllWithPage(Search search) {
		// TODO Auto-generated method stub
		return this._searchAndCount(Document.class,search);
	}

}
