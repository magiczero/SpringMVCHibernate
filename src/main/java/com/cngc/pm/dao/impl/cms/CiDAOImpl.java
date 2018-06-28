package com.cngc.pm.dao.impl.cms;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class CiDAOImpl extends BaseDAOImpl<Ci, Long> implements CiDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.cms.CiDAO#getByRelation(java.lang.String,
	 * java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ci> getByRelation(String relationId, Long primaryId) {
		List<Ci> cis = null;
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
	public List<Ci> getListByCode(String curcode) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("categoryCode", curcode);
		
		return this.search(search);
	}

	@Override
	public Ci getBySecurityNo(String securityNo) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("securityNo", securityNo);
		
		return this.searchUnique(search);
	}
	
	@Override
	public List<Ci> getListByCodeAndGroup(Category category, Group group, String reviewStatus) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("categoryCode", category.getCategoryCode());
		search.addFilterEqual("departmentInUse", group.getId());
		if(!reviewStatus.equals("00"))
			search.addFilterEqual("reviewStatus",reviewStatus);
		
		return this.search(search);
	}
	
	@Override
	public void merge(Ci ci) {
		// TODO Auto-generated method stub
		this._merge(ci);
	}
	
}
