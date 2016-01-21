package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.model.Records;
import com.cngc.pm.service.RecordsService;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	private RecordsDAO recordsDao;

	@Override
	@Transactional
	public void save(Records record) {
		// TODO Auto-generated method stub
		recordsDao.save(record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Records> getListAll() {
		// TODO Auto-generated method stub
		return recordsDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SearchResult<Records> getAllWithPage(Integer offset,
			Integer maxResults) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.setFirstResult(offset == null?0:offset);
		search.setMaxResults(maxResults==null?10:maxResults);
		search.addSort("id", true);
		
		return recordsDao.searchAndCount(search);
	}
}
