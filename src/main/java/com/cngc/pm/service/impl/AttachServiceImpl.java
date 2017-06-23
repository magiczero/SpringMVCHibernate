package com.cngc.pm.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AttachmentDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.service.AttachService;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	AttachmentDAO attachDao;
	
	@Autowired
	private RecordsDAO recordsDao;
	
	@Override
	@Transactional
	public Attachment create(Attachment attach, String username) {
		// TODO Auto-generated method stub
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		record.setDesc(username+"上传了文件，文件名：[" + attach.getName() +"]");
		
		recordsDao.save(record);
		
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
