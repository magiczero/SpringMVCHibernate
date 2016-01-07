package com.cngc.pm.dao.cms;

import java.util.List;

import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface CiDAO extends GenericDAO<Ci, Long> {

	/**
	 * 根据关系Id和主分类代码获取与之相关的配置项列表
	 * 
	 * @param relationId
	 * @param primaryId
	 * @return
	 */
	List<Ci> getByRelation(String relationId, Long primaryId);

	/**
	 * 删除分类之间的关系关联
	 * 
	 * @param primaryId
	 * @param secondaryId
	 * @param relationId
	 * @return
	 */
	boolean deleteRelation(Long primaryId, Long secondaryId, String relationId);

	/**
	 * 保存分类之间的关系关联
	 * 
	 * @param primaryId
	 * @param secondaryId
	 * @param relationId
	 * @return
	 */
	boolean saveRelation(Long primaryId, Long secondaryId, String relationId);
	
	SearchResult<Ci> getByIds(List<Long> ids);
	
	SearchResult<Ci> getByUser(String user);
}
