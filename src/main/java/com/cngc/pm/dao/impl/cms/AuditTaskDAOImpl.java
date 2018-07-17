package com.cngc.pm.dao.impl.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.AuditTaskDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class AuditTaskDAOImpl extends BaseDAOImpl<AuditTask,Long> implements AuditTaskDAO {


	@Override
	public List<AuditTask> getListByAssessor(SysUser assessor,int iDisplayStart,int iDisplayLength) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("assessor", assessor);
		
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		//search.addSort("endTime", true);
		search.addSort("startTime", true);
		
		return this.search(search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuditTask> getListByGroup(Group group,int iDisplayStart,int iDisplayLength) {
		// TODO Auto-generated method stub
		String hql = "from AuditTask task LEFT JOIN task.relationSet relation where relation.ciDepartment =:g0 group by task.id ,task.startTime  order by task.startTime desc";
		Query query = this.getSession().createQuery(hql);
		 
		query.setParameter("g0", group);
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);
		
		return query.list();
	}

	@Override
	public void update(AuditTask at) {
		// TODO Auto-generated method stub
		this._update(at);
	}

	@Override
	public AuditTask getByGroupAndCode(Group group, Category category) {
		// TODO Auto-generated method stub
		//String hql = "from AuditTask task LEFT JOIN task.relationSet relation where relation.ciDepartment =:g0 and relation.ciCategory=:c1 and relation.status>1";
		String hql = "from AuditTask task LEFT JOIN task.relationSet relation where relation.ciDepartment=:g0 and relation.ciCategory=:c1 and task.endTime is null";		//等于0表示未提交任务，也就是未完成的任务，逻辑关系修改后造成的
		Query query = this.getSession().createQuery(hql);
		 
		query.setParameter("g0", group);
		query.setParameter("c1", category);
//		if(commit) 
//			query.setParameter("s2", 1);
//		else
//			query.setParameter("s2", 0);
		return (AuditTask)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchResult<Ci> getCiList(Long atId, Collection<String> groupIdSet, Collection<String> codeSet,
			int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		SearchResult<Ci> result = new SearchResult<>();
		
		String sqlCount = "select count(*) as count_ from cms_ci ci left join wk_change_item item on ci.id=item.ci_id WHERE ci.delete_status='01' and ci.category_code in(:codes) and ci.department_in_use IN(:groups) AND item.change_id=:atid";
		
		Object obj0 = this.getSession().createSQLQuery(sqlCount).addScalar("count_", IntegerType.INSTANCE).setLong("atid", atId).setParameterList("codes", codeSet).setParameterList("groups", groupIdSet).uniqueResult();
		
		result.setTotalCount((Integer)obj0);
		
		String sqlCiList = "select ci.*, a.code_name name0, b.category_name, c.group_name, d.name, e.code_name name1, f.code_name name2 from cms_ci ci left join wk_change_item item on ci.id=item.ci_id left join sys_code a on ci.security_level=a.code_ left join cms_category b on ci.category_code=b.category_code left join sys_group c on ci.department_in_use=c.id left join sys_users d on ci.user_in_maintenance=d.username left join sys_code e on ci.review_status=e.code_ left join sys_code f on ci.status_=f.code_ WHERE ci.delete_status='01' AND item.change_id=:atid and ci.category_code in(:codes) and ci.department_in_use IN(:groups) AND a.type_='CI_SECURITY_LEVEL' and e.type_='CI_REVIEW_STATUS' and f.type_='CI_STATUS'";
		
		List<Object[]> list = this.getSession().createSQLQuery(sqlCiList).setLong("atid", atId).setParameterList("codes", codeSet).setParameterList("groups", groupIdSet).setFirstResult(iDisplayStart).setMaxResults(iDisplayLength).list();
		
		List<Ci> list0 = new ArrayList<>();
		
		for(Object[] obj : list) {
			Ci ci = new Ci();
			ci.setId(((java.math.BigInteger)obj[0]).longValue());			//id
			ci.setName((String)obj[1]);		//name
			ci.setModel((String)obj[2]);
			ci.setUse((String)obj[3]);
			ci.setSecurityNo((String)obj[4]);
			ci.setSecurityLevel((String)obj[5]);
			ci.setSecurityLevelName((String)obj[38]);
			ci.setCategoryCode((String)obj[7]);
			ci.setCategoryName((String)obj[39]);
			ci.setLocation((String)obj[10]);
			ci.setDepartmentInUse((String)obj[11]);
			ci.setDepartmentInUseName((String)obj[40]);
			ci.setUserInMaintenance((String)obj[13]);
			ci.setUserInMaintenanceName((String)obj[41]);
			ci.setServiceStartTime((java.sql.Date)obj[14]);
			ci.setReviewStatus((String)obj[19]);
			ci.setReviewStatusName((String)obj[42]);
			ci.setProducer((String)obj[23]);
			ci.setStatus((String)obj[24]);
			ci.setStatusName((String)obj[43]);
			ci.setPurpose((String)obj[25]);
			ci.setRemark(obj[31]==null?"":(String)obj[31]);
			ci.setPropertiesData((String)obj[32]);
			ci.setNum((String)obj[34]);
			ci.setSerial((String)obj[35]);
			list0.add(ci);
		}
		
		result.setResult(list0);
		
		return result;
	}

	@Override
	public int getCountByCondition(long at, Long groupId, String categoryCode, boolean isCommit, String status) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) as count_ from cms_ci ci left join wk_change_item item on ci.id=item.ci_id where ci.delete_status='01' AND item.change_id=:atid and ci.category_code =:code0 and ci.department_in_use=:group0";
		
		if(isCommit) {
			sqlCount += " and ci.review_status =:status0";
		} else
			sqlCount += " and item.passed_=:status0";
		
		Object obj0 = this.getSession().createSQLQuery(sqlCount).addScalar("count_", IntegerType.INSTANCE)
				.setLong("atid", at).setString("code0", categoryCode).setString("group0", groupId.toString()).setString("status0", status).uniqueResult();
		
		return (Integer)obj0;
	}


}
