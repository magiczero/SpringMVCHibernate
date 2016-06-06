package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.NoticeDAO;
import com.cngc.pm.model.Notice;
import com.cngc.pm.service.NoticeService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDAO noticeDao;
	@Override
	@Transactional
	public void save(Notice notice){
		noticeDao.save(notice);
	}
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		noticeDao.removeById(id);
		return true;
	}
	@Override
	@Transactional
	public Notice getById(Long id){
		return noticeDao.find(id);
	}
	@Override
	@Transactional
	public List<Notice> getAll(){
		return noticeDao.findAll();
	}
	@Override
	@Transactional
	public SearchResult<Notice> getLastNotice(){
		return noticeDao.getLastNotice();
	}

}
