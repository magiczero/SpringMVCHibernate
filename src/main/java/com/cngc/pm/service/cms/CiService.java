package com.cngc.pm.service.cms;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.SearchResult;

public interface CiService {

	void save(Ci ci);

	boolean delById(Long id);

	Ci getById(Long id);

	List<Ci> getAll();

	List<Ci> getByRelation(String relationId, Long primaryId);
	
	SearchResult<Ci> getByCategoryCode(String code);

	boolean deleteRelation(Long primaryId, Long secondaryId, String relationId);

	boolean saveRelation(Long primaryId, Long secondaryId, String relationId);
	
	SearchResult<Ci> getByIds(List<Long> ids);
	
	Map<String,Object> getStats(String column,String row,String status);
	
	SearchResult<Ci> getByUser(String user);

	/**
	 * 导入Excel文件数据（一个或多个Excel文件）
	 * @param setAttach		excel附件集合
	 * @throws IOException 
	 * @throws ParseException 
	 */
	void importData(Set<Attachment> setAttach) throws IOException, ParseException;

	/**
	 * 分页获取CI，根据code
	 * @param categoryCode
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	SearchResult<Ci> getAllWithPage(String categoryCode,int iDisplayStart, int iDisplayLength);
}
