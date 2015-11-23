package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.RecordDAO;
import com.cngc.pm.model.Record;
import com.cngc.pm.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	private RecordDAO recordDao;
	
	@Override
	@Transactional
	public List<Record> getAll()
	{
		return recordDao.findAll();
	}
}
