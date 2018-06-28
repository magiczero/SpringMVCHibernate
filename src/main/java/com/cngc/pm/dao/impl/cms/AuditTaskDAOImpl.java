package com.cngc.pm.dao.impl.cms;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.AuditTaskDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.googlecode.genericdao.search.Search;

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
		String hql = "from AuditTask task LEFT JOIN task.relationSet relation where relation.ciDepartment =:g0 and relation.ciCategory=:c1 and relation.status>1";
		Query query = this.getSession().createQuery(hql);
		 
		query.setParameter("g0", group);
		query.setParameter("c1", category);
		
		return (AuditTask)query.uniqueResult();
	}


}
