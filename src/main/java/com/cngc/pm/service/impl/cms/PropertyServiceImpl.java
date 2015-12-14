package com.cngc.pm.service.impl.cms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.PropertyService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDAO propertyDao;

	@Override
	@Transactional
	public void save(Property contract) {
		propertyDao.save(contract);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		propertyDao.removeById(id);
		return true;
	}

	@Override
	@Transactional
	public Property getById(Long id) {
		return propertyDao.find(id);
	}

	@Override
	@Transactional
	public List<Property> getAll() {
		return propertyDao.findAll();
	}

	@Override
	@Transactional
	public Set<Property> getPropertyByIds(String ids) {
		return propertyDao.getSet(ids);
	}

	@Override
	@Transactional
	public List<Property> getPropertyByNIds(List<Long> ids) {
		return propertyDao.getSetByNIds(ids);
	}
	@Override
	@Transactional
	public List<Property> getFields()
	{
		return propertyDao.getFields();
	}
	@Override
	@Transactional
	public SearchResult<Property> getByPropertyIds(String propertyIds)
	{
		return propertyDao.getByPropertyIds(propertyIds);
	}
}
