package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.KnowledgeDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.KnowledgeService;
import com.googlecode.genericdao.search.SearchResult;

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
	public boolean delById(Long id)
	{
		knowledgeDao.removeById(id);
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
	@Override
	@Transactional
	public Map<String,Object> getCountByStatus()
	{
		return knowledgeDao.getCountByStatus();
	}
	@Override
	@Transactional
	public SearchResult<Knowledge> getByStatus(String status)
	{
		return knowledgeDao.getByStatus(status);
	}
	@Override
	@Transactional
	public SearchResult<Knowledge> getNotFinished()
	{
		return knowledgeDao.getNotFinished();
	}
	@Override
	@Transactional
	public Map<String,Object> getCountByCategory(String startTime,String endTime)
	{
		return knowledgeDao.getCountByCategory(startTime,endTime);
	}
	@Override
	@Transactional
	public SearchResult<Knowledge> getSearchResult(String keyword, Integer offset,Integer maxResults)
	{
		return knowledgeDao.getSearchResult(keyword, offset, maxResults);
	}
	@Override
	@Transactional
	public boolean addHits(Knowledge knowledge)
	{
		return knowledgeDao.addHits(knowledge);
	}
	@Override
	@Transactional
	public List<Knowledge> getLastRead()
	{
		return knowledgeDao.getLastRead();
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status)
	{
		return knowledgeDao.getStats(column, row, startTime, endTime, status);
	}
	@Override
	@Transactional
	public SearchResult<Knowledge> getByIds(List<Long> ids)
	{
		return knowledgeDao.getByIds(ids);
	}
}
