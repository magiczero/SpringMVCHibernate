package com.cngc.pm.dao.cms;

import java.util.List;

import com.cngc.pm.model.cms.CategoryRelation;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface CategoryRelationDAO extends GenericDAO<CategoryRelation, Long> {

	/**
	 * 根据主分类代码获取与之相关的分类关联
	 * 
	 * @param code
	 * @return
	 */
	List<CategoryRelation> getByPrimaryCode(String code);
}
