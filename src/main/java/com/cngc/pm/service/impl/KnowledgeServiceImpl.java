package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.KnowledgeDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.KnowledgeService;

@Service
public class KnowledgeServiceImpl implements KnowledgeService{

	@Autowired
	private KnowledgeDAO knowledgeDao;
	
	@Override
	@Transactional
	public void save(Knowledge knowledge){
		knowledgeDao.save(knowledge);
	}
	
	@Override
	@Transactional
	public boolean delByIds(String ids)
	{
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 idss[i] = Long.valueOf(str);
		}
		try {
			for(Long k : idss) {
				knowledgeDao.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public Knowledge getById(Long id){
		return knowledgeDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Knowledge> getAll()
	{
		return knowledgeDao.findAll();
	}
}
