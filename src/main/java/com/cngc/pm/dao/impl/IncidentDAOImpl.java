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

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.ItilRelation;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class IncidentDAOImpl extends BaseDAOImpl<Incident, Long> implements IncidentDAO {
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
				.createCriteria(Incident.class)
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
	public SearchResult<Incident> getByIds(List<Long> ids)
	{
		Search search = new Search();
		search.addFilterIn("id", ids);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Incident> getByApplyUser(String user)
	{
		Search search = new Search();
		search.addFilterEqual("applyUser", user);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Incident> getByStatus(String status)
	{
		Search search = new Search();
		search.addFilterEqual("status", status);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Incident> search(String abs,String applyUser,String engineer,String satisfaction,Date startTime,Date endTime)
	{
		Search search = new Search();
		if(applyUser!=null)
		{
			if(!applyUser.isEmpty())
				search.addFilterEqual("applyUser", applyUser);
		}
		if(engineer!=null)
		{
			if(!engineer.isEmpty())
				search.addFilterEqual("currentDelegateUser", engineer);
		}
		if(satisfaction!=null)
			search.addFilterEqual("satisfaction", satisfaction);
		if(startTime!=null)
			search.addFilterGreaterOrEqual("applyTime", startTime);
		if(endTime!=null)
			search.addFilterLessOrEqual("recoverTime", endTime);
		if(abs!=null)
		{
			if(!abs.isEmpty())
				search.addFilterLike("abs", "%"+abs+"%");
		}
		
		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<Incident> getNotFinished()
	{
		Search search = new Search();
		search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getCountByCategory(String startTime,String endTime) {

		String sql = "SELECT COUNT(*) AS COUNT,SUBSTR(category,1,2) AS category FROM wk_incident "
				+" WHERE DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' "
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
				String sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_incident ";
				if(rowName.equals("id"))
					sql += " where "+rowName+" in(select primary_id from wk_itil_relation where secondary_id="+entry.getKey()+" and relation_type='INCIDENT_CI')";
				else
					sql += " where "+rowName+"='" + entry.getKey() + "'" ;
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
						+" FROM wk_incident i RIGHT JOIN wk_itil_relation r ON i.id=r.primary_id AND r.relation_type='INCIDENT_CI' "
						+" WHERE DATE(i.apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
				if(status!=null)
				{
					if(!status.equals("00"))
						sql += " AND status_='"+status+"' "; 
				}
				sql += " GROUP BY R.SECONDARY_ID ";
			}
			else
			{
				sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_incident "
						+"  WHERE DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
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
				name = "current_delegate_user";
				break;
			case "APPLY":
				name= "apply_user";
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
				list = syscodeDao.getCodeByType("INCIDENT_STATUS").getResult();
				break;
			case "SOURCE":
				list = syscodeDao.getCodeByType("INCIDENT_SOURCE").getResult();
				break;
			case "CATEGORY":
				list = syscodeDao.getCodeByType("INCIDENT_CATEGORY").getResult();
				break;
			case "SATISFACTION":
				list = syscodeDao.getCodeByType("INCIDENT_SATISFACTION").getResult();
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
			case "APPLY":
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
				List<Object> years = this.getSession().createSQLQuery("SELECT DISTINCT YEAR(apply_time) FROM wk_incident  ").list();
				for(Object obj:years)
					map.put(obj.toString(), obj.toString()+"年");
				break;
			}
			break;
		}	
		
		return map;
	}
}
