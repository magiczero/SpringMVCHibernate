package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.service.cms.CategoryService;
import com.googlecode.genericdao.search.Search;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDao;

	@Override
	@Transactional
	public void save(Category category) {
		categoryDao.save(category);
	}

	@Override
	@Transactional
	public boolean delByCode(String code) {
		categoryDao.removeById(code);
		return true;
	}

	@Override
	@Transactional
	public Category getByCode(String code) {

		return categoryDao.find(code);

	}

	@Override
	@Transactional
	public List<Category> getAll() {
		// return categoryDao.findAll();
		return categoryDao.getCategoryOrderByCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.service.cms.CategoryService#getJSON()
	 */
	@Override
	@Transactional
	public String getJSON() {
		String sjson = "";

		List<Category> list = categoryDao.getCategoryOrderByCode();

		String lastcode = "";
		String curcode = "";
		for (Category category : list) {
			curcode = category.getCategoryCode();
			if (lastcode.length() > 0) {
				if (curcode.length() > lastcode.length()) {
					// 子节点
					sjson += ",\"collapsed\":true,\"children\":[";
				} else if (curcode.length() == lastcode.length()) {
					// 兄弟节点
					sjson += "},";
				} else if (curcode.length() < lastcode.length()) {
					// 父级节点
					sjson += "}";
					for (int i = 0; i < lastcode.length() - curcode.length(); i = i + 2) {
						sjson += "]}";
					}
					sjson += ",";
				}
			}
			sjson += "{\"text\":\"<a href='javascript:void(0);' onclick='initTable(\\\""+category.getCategoryCode()+"\\\");'>" + category.getCategoryCode() + " " + category.getCategoryName() + "\"";
			lastcode = curcode;
		}
		sjson += "}";
		for (int i = 2; i < lastcode.length(); i = i + 2) {
			sjson += "]}";
		}
		sjson = "[" + sjson + "]";

		return sjson;
	}

	@Override
	@Transactional
	public Category getByName(String categoryName) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("categoryName", categoryName);
		
		return categoryDao.searchUnique(search);
	}

}
