package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.cms.CiService;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class CiServiceImpl implements CiService{
	
	@Autowired
	private CiDAO ciDao;
	

	@Override
	@Transactional
	public void save(Ci ci){
		ciDao.save(ci);
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		ciDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Ci getById(Long id){
		return ciDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Ci> getAll()
	{
		return ciDao.findAll();
	}
	
	@Override
	@Transactional
	public List<Ci> getByRelation(String relationId,Long primaryId)
	{
		return ciDao.getByRelation(relationId, primaryId);
	}
	@Override
	@Transactional
	public SearchResult<Ci> getByCategoryCode(String code)
	{
		Search search = new Search();
		search.addFilterLike("categoryCode", code+"%");
		return ciDao.searchAndCount(search);
	}
	@Override
	@Transactional
	public boolean deleteRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.deleteRelation(primaryId, secondaryId, relationId);
	}
	@Override
	@Transactional
	public boolean saveRelation(Long primaryId,Long secondaryId,String relationId)
	{
		return ciDao.saveRelation(primaryId, secondaryId, relationId);
	}
}
