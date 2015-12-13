package com.cngc.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.model.ItilRelation;
import com.cngc.pm.service.ItilRelationService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class ItilRelationServiceImpl implements ItilRelationService{
	@Autowired
	private ItilRelationDAO itilRelationDao;
	
	@Override
	@Transactional
	public boolean save(ItilRelation relation)
	{
		return itilRelationDao.save(relation);
	}
	@Override
	@Transactional
	public boolean save(Long primaryId,Long secondaryId,String type)
	{
		ItilRelation relation = new ItilRelation();
		relation.setPrimaryId(primaryId);
		relation.setSecondaryId(secondaryId);
		relation.setRelationType(type);
		return save(relation);
	}
	
	@Override
	@Transactional
	public SearchResult<ItilRelation> getByType(Long id,String type,boolean isprimary)
	{
		return itilRelationDao.getByType(id,type,isprimary);
	}
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		return itilRelationDao.removeById(id);
	}
	@Override
	@Transactional
	public boolean delById(Long primaryId,Long secondaryId,String type)
	{
		return itilRelationDao.delById(primaryId, secondaryId, type);
	}
}
