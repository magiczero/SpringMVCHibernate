package com.cngc.pm.dao.cms;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.cms.Property;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface PropertyDAO extends GenericDAO<Property, Long> {
	/**
	 * 根据id字符串获取属性组合
	 * 
	 * @param ids
	 * @return
	 */
	Set<Property> getSet(String ids);

	/**
	 * 获取不在id字符串中的属性组合
	 * 
	 * @param ids
	 * @return
	 */
	List<Property> getSetByNIds(List<Long> ids);
	
	List<Property> getFields();
	SearchResult<Property> getByPropertyIds(String propertyIds);
	
	String getPropertyNameByPropertyId(String propertyId);
}
