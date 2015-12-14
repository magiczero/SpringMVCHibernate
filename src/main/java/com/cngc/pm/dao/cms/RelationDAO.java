package com.cngc.pm.dao.cms;

import java.util.List;

import com.cngc.pm.model.cms.Relation;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface RelationDAO extends GenericDAO<Relation, String> {

	SearchResult<Relation> getByIds(List<String> ids);
}
