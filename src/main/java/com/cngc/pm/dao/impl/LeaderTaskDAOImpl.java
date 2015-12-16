package com.cngc.pm.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.LeaderTaskDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class LeaderTaskDAOImpl extends BaseDAOImpl<LeaderTask,Long> implements LeaderTaskDAO{
	@Autowired
	private SysCodeDAO syscodeDao;
	@Autowired
	private UserDAO userDao;

	@Override
	public SearchResult<LeaderTask> getNotFinishedTask()
	{
		Search search = new Search();
		search.addFilterEqual("executionTime", null);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<LeaderTask> search(String startTime,String endTime)
	{
		Search search = new Search();
		search.addFilterCustom("DATE(apply_time)>='"+startTime+"'");
		search.addFilterCustom("DATE(apply_time)<='"+endTime+"'");
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
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
				String sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_task ";
				sql += " where "+rowName+"='" + entry.getKey() + "'" ;
				sql += " AND DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
				sql += " AND execution_time IS NOT NULL ";
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
			sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM wk_task "
						+"  WHERE DATE(apply_time) BETWEEN '"+startTime+"'  AND '"+endTime+"' ";
			sql += " AND execution_time IS NOT NULL ";
			sql += "  GROUP BY " + columnName;;

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
				name = "to_user";
				break;
			case "APPLY":
				name= "from_user";
			}
			break;
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
		case "DATE":
			switch(name)
			{
			case "MONTH":
				for(Integer i=1;i<=12;i++)
					map.put(i.toString(), i.toString()+"月");
				break;
			case "YEAR":
				List<Object> years = this.getSession().createSQLQuery("SELECT DISTINCT YEAR(apply_time) FROM wk_task  ").list();
				for(Object obj:years)
					map.put(obj.toString(), obj.toString()+"年");
				break;
			}
			break;
		}	
		
		return map;
	}
}
