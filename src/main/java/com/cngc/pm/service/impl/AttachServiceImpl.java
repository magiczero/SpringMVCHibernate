package com.cngc.pm.service.impl;

import java.util.HashSet;
import java.util.Set;

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

	@Override
	@Transactional(readOnly=true)
	public Set<Attachment> getSetByIds(String attachIds) {
		// TODO Auto-generated method stub
		String[] ids = attachIds.split(",");
		
		Set<Attachment> set = new HashSet<>();
		
		for(String id : ids) {
			Attachment attach = attachDao.find(Long.valueOf(id));
			set.add(attach);
		}
		
		return set;
	}

	@Override
	@Transactional
	public boolean delById(long attachid) {
		// TODO Auto-generated method stub
		Attachment attach = attachDao.find(attachid);
		if(attach==null)
			return false;
		
		return attachDao.remove(attach);
	}

}
