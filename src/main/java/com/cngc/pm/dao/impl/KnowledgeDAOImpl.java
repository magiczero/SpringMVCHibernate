package com.cngc.pm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.KnowledgeDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class KnowledgeDAOImpl extends BaseDAOImpl<Knowledge,Long> implements KnowledgeDAO {
	@Autowired
	private StatsDAO statsDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,Object> getCountByStatus() {

		List list = this.getSession()
				.createCriteria(Knowledge.class)
				.setProjection(
						Projections.projectionList().add(Projections.rowCount())
								.add(Projections.groupProperty("status"))).list();
		Map<String,Object> map = new HashMap<String,Object>();
		for(Object element:list)
		{
			Object[] objs = (Object[])element;
			map.put(objs[1].toString(), objs[0].toString());
		}
		return map;
	}
	@Override
	public SearchResult<Knowledge> getByIds(List<Long> ids)
	{
		Search search = new Search();
		search.addFilterIn("id", ids);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Knowledge> getByStatus(String status)
	{
		Search search = new Search();
		search.addFilterEqual("status", status);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Knowledge> getNotFinished()
	{
		Search search = new Search();
		search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.knowledge.status.published"));
		search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.knowledge.status.deleted"));
		
		return this.searchAndCount(search);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String,Object> getCountByCategory(String startTime,String endTime) {

		String sql = "SELECT COUNT(*) AS COUNT,SUBSTR(category,1,2) AS category FROM wk_knowledge "
				+" WHERE DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' AND status_=" 
				+ PropertyFileUtil.getStringValue("syscode.knowledge.status.published")
				+" GROUP BY SUBSTR(category,1,2)";
		
		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String,Object> map = new HashMap<String,Object>();
		for(Object element:list)
		{
			Object[] objs = (Object[])element;
			map.put(objs[1].toString(), objs[0].toString());
		}
		return map;
	}
	@Override
	public SearchResult<Knowledge> getSearchResult(String keyword, Integer offset,Integer maxResults)
	{
		Search search = new Search();
		search.setFirstResult(offset == null?0:offset);
		search.setMaxResults(maxResults==null?10:maxResults);
		if(keyword!=null)
		{
			search.addFilterOr(Filter.like("title", "%"+keyword+"%"),
					Filter.like("keyword","%"+keyword+"%"));
		}
		search.addFilterAnd(Filter.equal("status", PropertyFileUtil.getStringValue("syscode.knowledge.status.published")));
		search.addSort("id", true);
		
		return this.searchAndCount(search);
	}
	@Override
	public boolean addHits(Knowledge knowledge)
	{
		if(knowledge.getHits()==null)
			knowledge.setHits(Long.valueOf(1));
		else
			knowledge.setHits(knowledge.getHits()+1);
		knowledge.setLastReadTime(new Date());
		
		this.save(knowledge);
		
		return true;
	}
	@Override
	public List<Knowledge> getLastRead()
	{
		Search search = new Search();
		search.setFirstResult(0);
		search.setMaxResults( PropertyFileUtil.getIntValue("knowledge.maxlastread") );
		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.knowledge.status.published"));
		search.addSort("lastReadTime",true);
		SearchResult<Knowledge> result = this.searchAndCount(search);
		
		return result.getResult();
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status)
	{
		return statsDao.getStats("KNOWLEDGE", column, row, startTime, endTime, status);
	}
	
}
