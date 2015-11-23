package com.cngc.pm.dao.cms;

import java.util.List;

import com.cngc.pm.model.cms.Category;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface CategoryDAO extends GenericDAO<Category, String> {

	/**获取分类列表，并按code排序
	 * @return
	 */
	List<Category> getCategoryOrderByCode();
}
