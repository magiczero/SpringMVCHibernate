package com.cngc.pm.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.dao.KnowledgeDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.model.ItilRelation;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class KnowledgeDAOImpl extends BaseDAOImpl<Knowledge,Long> implements KnowledgeDAO {
	@Autowired
	private SysCodeDAO syscodeDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private ItilRelationDAO itilRelationDao;
	
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
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> colmap = new HashMap<String,Object>();
		Map<String,Object> rowmap = new HashMap<String,Object>();
		
		List collist=null,rowlist=null;
		String columnPrefix=null,columnName=null,rowPrefix=null,rowName=null;
		//读取column分类
		if(column!=null)
		{
			int icol = column.indexOf('_');
			if(icol>=0)
			{
				columnPrefix = column.substring( 0, icol );
				columnName = column.substring( icol + 1 );
				colmap =  getCategoryList(columnPrefix,columnName);
				result.put("column", colmap);
				columnName = getCategoryName(columnPrefix,columnName);
			}
		}
		//读取row分类
		if(row!=null)
		{
			int irow = row.indexOf('_');
			if(irow>=0)
			{
				rowPrefix = row.substring( 0, irow );
				rowName = row.substring( irow + 1 );
				rowmap = getCategoryList(rowPrefix,rowName);
				result.put("row", rowmap);
				rowName = getCategoryName(rowPrefix,rowName);
			}
		}
		//开始统计
		if(row!=null)
		{
			//二维统计
			for(Map.Entry<String,Object> entry : rowmap.entrySet())
			{
				String sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_knowledge ";
				if(rowName.equals("id"))
					sql += " where "+rowName+" in(select primary_id from wk_itil_relation where secondary_id="+entry.getKey()+" and relation_type='INCIDENT_CI')";
				else
					sql += " where "+rowName+"='" + entry.getKey() + "'" ;
				if(startTime!=null)
					sql += " AND DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
				if(status!=null)
				{
					if(!status.equals("00"))
						sql += " AND status_='"+status+"' "; 
				}
				sql += " GROUP BY " + columnName;;
				Query query = this.getSession().createSQLQuery(sql);
				List list = query.list();
				Map<String,Object> counts = new HashMap<String,Object>();
				for(Object element:list)
				{
					Object[] objs = (Object[])element;
					counts.put(objs[1].toString(), objs[0].toString());
				}
				
				result.put(entry.getKey(), counts);
			}
		}
		else
		{
			//一维统计
			String sql = "";
			if(columnName.equals("id"))
			{
				sql = "SELECT COUNT(*) AS COUNT,R.secondary_id AS COLUMNID " 
						+" FROM wk_knowledge i RIGHT JOIN wk_itil_relation r ON i.id=r.primary_id AND r.relation_type='INCIDENT_CI' ";
				if(startTime!=null)
					sql += " WHERE DATE(i.apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
				if(status!=null)
				{
					if(!status.equals("00"))
						sql += " AND status_='"+status+"' "; 
				}
				sql += " GROUP BY R.SECONDARY_ID ";
			}
			else
			{
				sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_knowledge ";
				if(startTime!=null)
					sql += "  WHERE DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
				if(status!=null)
				{
					if(!status.equals("00"))
						sql += " AND status_='"+status+"' "; 
				}
				sql += "  GROUP BY " + columnName;;
			}

			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String,Object> counts = new HashMap<String,Object>();
			for(Object element:list)
			{
				Object[] objs = (Object[])element;
				counts.put(objs[1].toString(), objs[0].toString());
			}
			
			result.put("counts", counts);
		}
		
		return result;
	}
	private String getCategoryName(String prefix,String name)
	{
		switch(prefix)
		{
		case "CODE":
			switch(name)
			{
			case "STATUS":
				name = "status_";
				break;
			}
			break;
		case "USER":
			switch(name)
			{
			case "ENGINEER":
				name = "apply_user";
				break;
			}
			break;
		case "CI":
			switch(name)
			{
			case "ASSET":
				name = "id";
				break;
			}
		case "DATE":
			switch(name)
			{
			case "MONTH":
				name = "MONTH(apply_time)";
				break;
			case "YEAR":
				name = "YEAR(apply_time)";
				break;
			}
			break;
		}	
		return name;
	}
	private Map<String,Object> getCategoryList(String prefix,String name)
	{
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		List list = null;
		switch(prefix)
		{
		case "CODE":
			switch(name)
			{
			case "STATUS":
				list = syscodeDao.getCodeByType("KNOWLEDGE_STATUS").getResult();
				break;
			case "CATEGORY":
				list = syscodeDao.getCodeByType("INCIDENT_CATEGORY").getResult();
				break;
			default:
				list = syscodeDao.getCodeByType(name).getResult();
			}
				
			for(Object code:list)
				map.put( ((SysCode)code).getCode(), ((SysCode)code).getCodeName() );
			break;
		case "USER":
			switch(name)
			{
			case "ENGINEER":
				list = userDao.findAll();
				break;
			}

			for(Object code:list)
				map.put( ((SysUser)code).getUsername(), ((SysUser)code).getName() );
			break;
		case "CI":
			switch(name)
			{
			case "ASSET":
				List<ItilRelation> relations =  itilRelationDao.getByType("INCIDENT_CI").getResult();
				List<Long> ids = new ArrayList<Long>();
				for(ItilRelation relation:relations)
					ids.add(relation.getSecondaryId());
				list = ciDao.getByIds(ids).getResult();
				for(Object code:list)
					map.put( ((Ci)code).getId().toString(), ((Ci)code).getName() );
				break;
			}
			break;
		case "DATE":
			switch(name)
			{
			case "MONTH":
				for(Integer i=1;i<=12;i++)
					map.put(i.toString(), i.toString()+"月");
				break;
			case "YEAR":
				List<Object> years = this.getSession().createSQLQuery("SELECT DISTINCT YEAR(apply_time) FROM wk_knowledge  ").list();
				for(Object obj:years)
					map.put(obj.toString(), obj.toString()+"年");
				break;
			}
			break;
		}	
		
		return map;
	}
}
