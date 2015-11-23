package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.RelationDAO;
import com.cngc.pm.model.cms.Relation;
import com.cngc.pm.service.cms.RelationService;

@Service
public class RelationServiceImpl implements RelationService{
	
	@Autowired
	private RelationDAO relationDao;

	@Override
	@Transactional
	public void save(Relation relation){
		relationDao.save(relation);
	}
	
	@Override
	@Transactional
	public boolean delById(String id)
	{
		relationDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Relation getById(String id){
		
		return relationDao.find(id);
		
	}
	
	@Override
	@Transactional
	public List<Relation> getAll()
	{
		return relationDao.findAll();
	}
}
