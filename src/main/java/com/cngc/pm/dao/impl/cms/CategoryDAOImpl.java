package com.cngc.pm.dao.impl.cms;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.cms.Category;
import com.googlecode.genericdao.search.Search;

@Repository
public class CategoryDAOImpl extends BaseDAOImpl<Category, String> implements CategoryDAO {

	@Override
	public List<Category> getCategoryOrderByCode()
	{
		String hql = "from Category a order by a.categoryCode asc";
		Query query = this.getSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Category> list = query.list();
		
		return list;
	}

	@Override
	public Category getByName(String categoryName) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("categoryName", categoryName);
		
		return searchUnique(search);
	}

}
	