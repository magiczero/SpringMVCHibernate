package com.cngc.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.model.Records;
import com.cngc.pm.service.RecordsService;

@Service
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	private RecordsDAO recordsDao;

	@Override
	public void save(Records record) {
		// TODO Auto-generated method stub
		recordsDao.save(record);
	}
}
