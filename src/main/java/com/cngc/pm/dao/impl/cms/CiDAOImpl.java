package com.cngc.pm.dao.impl.cms;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class CiDAOImpl extends BaseDAOImpl<Ci, Long> implements CiDAO {
	@Autowired
	private SysCodeDAO syscodeDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private ItilRelationDAO itilRelationDao;
	@Autowired
	private CategoryDAO categoryDao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.CiDAO#getByRelation(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public List<Ci> getByRelation(String relationId, Long primaryId) {
		List<Ci> cis = null;
		@SuppressWarnings("unchecked")
		List<Long> secondaryIds = this.getSession()
				.createSQLQuery("select secondary_id from cms_ci_relation where primary_id=? and relation_id=?")
				.addScalar("secondary_id", LongType.INSTANCE).setLong(0, primaryId).setString(1, relationId).list();

		if (secondaryIds.size() > 0) {
			Query query = this.getSession().createQuery("from Ci a where a.id in(:ids)")
					.setParameterList("ids", secondaryIds);
			cis = query.list();
		}

		return cis;
	}
	@Override
	public SearchResult<Ci> getByUser(String user)
	{
		Search search = new Search();
		search.addFilterEqual("userInMaintenance", user);
		
		return this.searchAndCount(search);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.CiDAO#deleteRelation(java.lang.Long,
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean deleteRelation(Long primaryId, Long secondaryId, String relationId) {
		this.getSession()
				.createSQLQuery("delete from cms_ci_relation where primary_id=? and secondary_id=? and relation_id=?")
				.setLong(0, primaryId).setLong(1, secondaryId).setString(2, relationId).executeUpdate();

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.CiDAO#saveRelation(java.lang.Long,
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean saveRelation(Long primaryId, Long secondaryId, String relationId) {
		this.getSession()
				.createSQLQuery("insert into cms_ci_relation(primary_id,secondary_id,relation_id) values(?,?,?)")
				.setLong(0, primaryId).setLong(1, secondaryId).setString(2, relationId).executeUpdate();

		return true;
	}
	@Override
	public SearchResult<Ci> getByIds(List<Long> ids)
	{
		Search search = new Search();
		search.addFilterIn("id", ids);
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String status)
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
				String sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM cms_ci ";
				if(rowName.equals("id"))
					sql += " where "+rowName+" in(select primary_id from wk_itil_relation where secondary_id="+entry.getKey()+" and relation_type='INCIDENT_CI')";
				else
					sql += " where "+rowName+"='" + entry.getKey() + "'" ;
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
						+" FROM cms_ci i RIGHT JOIN wk_itil_relation r ON i.id=r.primary_id AND r.relation_type='INCIDENT_CI' ";
				if(status!=null)
				{
					if(!status.equals("00"))
						sql += " AND status_='"+status+"' "; 
				}
				sql += " GROUP BY R.SECONDARY_ID ";
			}
			else
			{
				sql = "SELECT COUNT(*) AS COUNT,"+columnName+" AS COLUMNID FROM cms_ci ";
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
			case "DELETE":
				name = "delete_status";
				break;
			case "REVIEW":
				name = "review_status";
				break;
			case "CATEGORY":
				name = "category_code";
			}
			break;
		case "USER":
			switch(name)
			{
			case "ENGINEER":
				name = "user_in_maintenance";
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
				list = syscodeDao.getCodeByType("CI_STATUS").getResult();
				break;
			case "DELETE":
				list = syscodeDao.getCodeByType("CI_DELETE_STATUS").getResult();
				break;
			case "REVIEW":
				list = syscodeDao.getCodeByType("CI_REVIEW_STATUS").getResult();
				break;
			case "CATEGORY":
				// list = syscodeDao.getCodeByType("INCIDENT_SATISFACTION").getResult();
				list = categoryDao.getCategoryOrderByCode();
				break;
			default:
				list = syscodeDao.getCodeByType(name).getResult();
			}
			if(name.equals("CATEGORY"))
			{
				for(Object code:list)
					map.put( ((Category)code).getCategoryCode(), ((Category)code).getCategoryName() );
			}
			else
			{
				for(Object code:list)
					map.put( ((SysCode)code).getCode(), ((SysCode)code).getCodeName() );
			}
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
		}	
		
		return map;
	}
}
