package com.cngc.pm.dao.impl.cms;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.cms.CategoryRelationDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.cms.CategoryRelation;

@Repository
public class CategoryRelationDAOImpl extends BaseDAOImpl<CategoryRelation, Long> implements CategoryRelationDAO {

	/*
	 * 根据主分类代码获取与之相关的分类关联
	 * 
	 * @see
	 * com.cngc.pm.dao.cms.CategoryRelationDAO#getByPrimaryCode(java.lang.String
	 * )
	 */
	@Override
	public List<CategoryRelation> getByPrimaryCode(String code) {
		String hql = "from CategoryRelation a where a.categoryCodePrimary=:code";
		Query query = this.getSession().createQuery(hql);

		query.setParameter("code", code);

		@SuppressWarnings("unchecked")
		List<CategoryRelation> list = query.list();

		return list;
	}
}
