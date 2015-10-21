package com.cngc.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AttachmentDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.service.AttachService;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	AttachmentDAO attachDao;
	
	@Override
	@Transactional
	public Attachment create(Attachment attach) {
		// TODO Auto-generated method stub
		return attachDao.create(attach);
	}

	@Override
	@Transactional(readOnly=true)
	public Attachment get(long id) {
		// TODO Auto-generated method stub
		return attachDao.find(id);
	}

}
