package com.cngc.pm.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.SearchResult;

public interface DocumentService {

	/**
	 * 根据文件名获得附件列表
	 * @param filenames	文件名列表
	 * @return
	 */
	Set<Attachment> getSetAttach(String... filenames);

	void save(Document document);

	List<Document> getAll(Long userid);
	
	Map<String, String> getMapStyle();

	Set<Style> getSetStyle(Long...ids );

	Style loadStyleById(Long id);
	
	List<Style> getListStyle();

	boolean delByIds(String ids);

	List<Document> getAllByStyle(Long userId, Long styleId);

	/**
	 * 获取自己的文档
	 * @param userId
	 * @return
	 */
	List<Document> getAllByPrivate(Long userId);
	
	boolean isEmptyDocsByUser(SysUser user);

	boolean delById(Long docid, Long id);

	List<Document> getAllLastVersion();

	Document getById(long docid);

	List<Style> getAllCheckItems();

	void update(Document document);

	List<Document> getByStyle(long styleid);

	List<Document> getByItem(long itemid);

	int getModuleParentIdByURL(String url);
	
	/**
	 * 分页查询
	 * @param offset 页码
	 * @param maxResults 每页数量 
	 * @return
	 */
	List<Document> getListWithPagination(Integer offset, Integer maxResults);

	SearchResult<Document> getAll(Integer offset,
			Integer maxResults);
	
	SearchResult<Document> getAllByStyle(Style style, Integer offset, Integer maxResults);
	
	SearchResult<Document> getAllByItem(Long itemid, Integer offset, Integer maxResults);
	
	/**
	 * 分页查询 根据code
	 * @param code
	 * @param offset
	 * @param maxResults
	 * @return
	 */
	SearchResult<Document> getListWithPageByCode(String code, Integer offset, Integer maxResults);

	List<Style> getStyleListByCode(String code);

	List<Document> getListByCode(String code);

	void saveStyle(Style style);

	boolean enabledByIds(String ids);

	String getJSONByCodes(List<SysCode> codeList);

	boolean delStyle(Long id);
	
	List<Document> getListByUserAndNum(String username, int num);
	
}
