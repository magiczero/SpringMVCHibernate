package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.CategoryRelationDAO;
import com.cngc.pm.model.cms.CategoryRelation;
import com.cngc.pm.service.cms.CategoryRelationService;

@Service
public class CategoryRelationServiceImpl implements CategoryRelationService {

	@Autowired
	private CategoryRelationDAO categoryRelationDao;

	@Override
	@Transactional
	public void save(CategoryRelation category) {
		categoryRelationDao.save(category);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		categoryRelationDao.removeById(id);
		return true;
	}

	@Override
	@Transactional
	public CategoryRelation getById(Long id) {

		return categoryRelationDao.find(id);

	}

	@Override
	@Transactional
	public List<CategoryRelation> getAll() {
		return categoryRelationDao.findAll();
	}

	@Override
	@Transactional
	public List<CategoryRelation> getByPrimaryCode(String code) {
		return categoryRelationDao.getByPrimaryCode(code);
	}
}
