package com.cngc.pm.dao.impl.cms;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.RelationDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.cms.Relation;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class RelationDAOImpl extends BaseDAOImpl<Relation, String> implements RelationDAO {

	@Override
	public SearchResult<Relation> getByIds(List<String> ids)
	{
		Search search = new Search();
		search.addFilterIn("relationId", ids);
		
		return this.searchAndCount(search);
	}
}
