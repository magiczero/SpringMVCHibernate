package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Resources;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ResourcesDAO extends GenericDAO<Resources, Long> {

	/**
	 * 按id倒序查找所有的资源
	 * @return
	 */
	List<Resources> findAllWithOrder();

	Resources update(Resources re);

	/**
	 * 根据路径匹配相关资源
	 * @param url
	 * @return
	 */
	Resources getByURL(String url);

}
