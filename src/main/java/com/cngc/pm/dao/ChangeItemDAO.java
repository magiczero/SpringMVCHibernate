package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.ChangeItem;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface ChangeItemDAO extends GenericDAO<ChangeItem,Long>{

	SearchResult<ChangeItem> getByCi(Long id);
	SearchResult<ChangeItem> getByCi(List<Long> ids);
	List<ChangeItem> getByChangeId(Long id);
}
