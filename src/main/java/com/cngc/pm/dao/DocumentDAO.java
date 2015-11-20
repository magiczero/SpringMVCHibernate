package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Document;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

public interface DocumentDAO extends GenericDAO<Document, Long> {

	List<Document> getListBySelfAndOpen(Long userId);

	List<Document> getListBySelfAndOpenAndStyle(Long userId, Long styleId);

	List<Document> getListBySelf(Long userId);

	List<Document> getListByLastVersion();

	Document update(Document document);

	List<Document> getByStyle(long styleid);

	List<Document> getByItem(long itemid);

	List<Document> getListWithPage(Integer offset, Integer maxResults);
	
	SearchResult<Document> getAllWithPage(Search search);
}
