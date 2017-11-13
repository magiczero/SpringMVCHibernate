package com.cngc.pm.dao.computer;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.computer.InspectionItem;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

/**
 * @author andy
 *
 */
public interface InspectionItemDAO extends GenericDAO<InspectionItem, Long>{

	/**
	 * 根据id字符串获取检查项组合
	 * @param ids
	 * @return
	 */
	Set<InspectionItem> getSet(String ids);

	List<InspectionItem> getSetByNIds(List<Long> ids);
	SearchResult<InspectionItem> getByIds(String ids);
}
