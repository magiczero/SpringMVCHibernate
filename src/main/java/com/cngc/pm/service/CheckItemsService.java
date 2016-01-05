package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.CheckItems;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.googlecode.genericdao.search.SearchResult;

public interface CheckItemsService {

	SearchResult<CheckItems> getAll(Long itemid, Integer offset, Integer maxResults);
	
	SearchResult<CheckItems> getAll(String code, Integer offset, Integer maxResults);
	
	List<Style> getSytleListByCode(String code);
	
	List<Document> getAllDoc();

	Document loadDocById(Long id);
	
	void save(CheckItems ci);
	
	SearchResult<CheckItems> getAllByLevelItemid(int level, long id);
	
	void save(Style items);
	
	Style getAllByCode();
	
	Style getStyleByCode(String code);
	
	/**
	 * 根据type id 获取code
	 * @param id
	 * @return
	 */
	String getCodeByTypeid(Long id);

	CheckItems find(Long id);
	
	boolean delete(Long id);
	
	/**
	 * 返回最下一级项的集合
	 * @param code
	 * @return
	 */
	List<Style> getAllItemsByCode(String code);
}
