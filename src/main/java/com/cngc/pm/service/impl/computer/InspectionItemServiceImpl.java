package com.cngc.pm.service.impl.computer;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.InspectionItemDAO;
import com.cngc.pm.model.computer.InspectionItem;
import com.cngc.pm.service.computer.InspectionItemService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class InspectionItemServiceImpl implements InspectionItemService{

	@Autowired
	private InspectionItemDAO itemDao;
	
	@Override
	@Transactional
	public void save(InspectionItem item) {
		// TODO 自动生成的方法存根
		itemDao.save(item);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		itemDao.removeById(id);
		return true;
	}

	@Override
	@Transactional
	public InspectionItem getById(Long id) {
		// TODO 自动生成的方法存根
		return itemDao.find(id);
	}

	@Override
	@Transactional
	public List<InspectionItem> getAll() {
		// TODO 自动生成的方法存根
		return itemDao.findAll();
	}

	@Override
	@Transactional
	public Set<InspectionItem> getItemByIds(String ids) {
		// TODO 自动生成的方法存根
		return itemDao.getSet(ids);
	}

	@Override
	@Transactional
	public List<InspectionItem> getItemByNIds(List<Long> ids) {
		// TODO 自动生成的方法存根
		return itemDao.getSetByNIds(ids);
	}

	@Override
	@Transactional
	public SearchResult<InspectionItem> getByIds(String ids) {
		// TODO 自动生成的方法存根
		return itemDao.getByIds(ids);
	}

}
