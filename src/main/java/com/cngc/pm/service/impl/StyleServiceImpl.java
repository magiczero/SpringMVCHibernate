package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.StyleService;
import com.googlecode.genericdao.search.Search;

@Service
public class StyleServiceImpl implements StyleService {

	@Autowired
	private StyleDAO styleDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Style> getAllByLevelFir() {
		// TODO Auto-generated method stub
		Search search = new Search(Style.class);
		
//		search.addFilterEqual("style.id", null);
		search.addFilterEmpty("style");
		
		return styleDao.search(search);
	}

	@Override
	@Transactional
	public void save(Style style) {
		// TODO Auto-generated method stub
		styleDao.save(style);
	}

}
