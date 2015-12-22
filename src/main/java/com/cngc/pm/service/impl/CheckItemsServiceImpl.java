package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.exception.ParameterException;
import com.cngc.pm.dao.CheckItemsDAO;
import com.cngc.pm.dao.DocumentDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.CheckItems;
import com.cngc.pm.model.Document;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.CheckItemsService;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class CheckItemsServiceImpl implements CheckItemsService {

	@Autowired
	private CheckItemsDAO itemsDao;
	
	@Autowired
	private StyleDAO styleDao;
	
	@Autowired
	private DocumentDAO docDao;

	@Override
	@Transactional(readOnly=true)
	public SearchResult<CheckItems> getAll(Long itemid, Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		Search search = new Search(CheckItems.class);
		search.setFirstResult(offset == null?0:offset);
		search.setMaxResults(maxResults==null?10:maxResults);
		//search.addFilterSome("checkItems", Filter.equal("id", itemid));
		search.addFilterEqual("item.id", itemid);
		search.addSort("item.style.style.style.style.id", true);
		search.addSortAsc("item.style.style.style.id");
		search.addSortAsc("item.style.style.id");
		search.addSortAsc("item.style.id");
		search.addSortAsc("item.id");
		
		return itemsDao.searchAndCount(search);
	}

	@Override
	@Transactional(readOnly=true)
	public SearchResult<CheckItems> getAll(String code, Integer offset, Integer maxResults) {
		// TODO Auto-generated method stub
		Search search = new Search(CheckItems.class);
		search.setFirstResult(offset == null?0:offset);
		search.setMaxResults(maxResults==null?10:maxResults);
		//search.addFilterSome("checkItems", Filter.equal("id", itemid));
		if("BMB22".equals(code)) {
			search.addFilterEqual("item.style.style.style.style.code", code);
			search.addSort("item.style.style.style.style.id", true);
		} else {
			search.addFilterEqual("item.style.style.style.code", code);
		}
		search.addSortAsc("item.style.style.style.id");
		search.addSortAsc("item.style.style.id");
		search.addSortAsc("item.style.id");
		search.addSortAsc("item.id");
		
		return itemsDao.searchAndCount(search);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Style> getSytleListByCode(String code) {
		// TODO Auto-generated method stub
		return styleDao.getAllByCode(code);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Document> getAllDoc() {
		// TODO Auto-generated method stub
		return docDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Document loadDocById(Long id) {
		// TODO Auto-generated method stub
		return docDao.find(id);
	}

	@Override
	@Transactional
	public void save(CheckItems ci) {
		// TODO Auto-generated method stub
		itemsDao.save(ci);
	}

	@Override
	@Transactional(readOnly=true)
	public SearchResult<CheckItems> getAllByLevelItemid(int level, long id) {
		// TODO Auto-generated method stub
//		//解析
//		String[] s = levelItemid.split("-");
//		int level = Integer.valueOf(s[0]);
//		long itemsid = Long.valueOf(s[1]);
		
		Style currStyle = styleDao.find(id);
		
		String code = getStyleParent(currStyle).getCode();
		
		Search search = new Search(CheckItems.class);
		
		if(code.equals("BMB20") || code.equals("BMB17")) {
			switch(level) {
				case 3:
					search.addFilterEqual("item.id", id);
					break;
				case 2:
					search.addFilterEqual("item.style.id", id);
					break;
				case 1:
					search.addFilterEqual("item.style.style.id", id);
					break;
			}
		} else if(code.equals("BMB22")) {
			switch(level) {
				case 4:
					search.addFilterEqual("item.id", id);
					break;
				case 3:
					search.addFilterEqual("item.style.id", id);
					break;
				case 2:
					search.addFilterEqual("item.style.style.id", id);
					break;
				case 1:
					search.addFilterEqual("item.style.style.style.id", id);
					break;
			}
		}
		
		return itemsDao.searchAndCount(search);
	}

	@Override
	@Transactional
	public void save(Style items) {
		// TODO Auto-generated method stub
		styleDao.save(items);
	}

	@Override
	@Transactional(readOnly=true)
	public Style getAllByCode() {
		// TODO Auto-generated method stub
		//return styleDao.getByCode("CI");
		Search search = new Search(Style.class);
		
		search.addFilterEqual("code", "CI");
		//search.addSort("item.style.style.style.style.id", true);
//		search.addSortAsc("style.style.style.style.order");
//		search.addSortAsc("style.style.style.order");
//		search.addSortAsc("style.style.order");
		search.addSortAsc("order");
		
		return styleDao.searchUnique(search);
	}

	@Override
	@Transactional(readOnly=true)
	public String getCodeByTypeid(Long id) {
		// TODO Auto-generated method stub
		Style style = styleDao.find(id);
		styleDao.refresh(style);
		return getStyleParent(style).getCode();
	}
	
	private Style getStyleParent(final Style style) {
		if(style.getCode() == null || style.getCode().equals("NON"))
			return getStyleParent(style.getStyle());
		else
			return style;
	}

	@Override
	@Transactional(readOnly=true)
	public CheckItems find(Long id) {
		// TODO Auto-generated method stub
		CheckItems ci = itemsDao.find(id);
		if(ci == null)
			throw new ParameterException("未找到相应的检查项");
		
		return ci;
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return itemsDao.remove(find(id));
	}

	@Override
	@Transactional
	public Style getStyleByCode(String code) {
		// TODO Auto-generated method stub
		Search search = new Search(Style.class);
		
		search.addFilterEqual("code", code);
		search.addSortAsc("order");
		
		return styleDao.searchUnique(search);
	}
	
}
