package com.cngc.pm.service.impl.opr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.opr.SoftwareWhiteDAO;
import com.cngc.pm.model.opr.SoftwareWhite;
import com.cngc.pm.service.opr.SoftwareWhiteService;

@Service
public class SoftwareWhiteServiceImpl implements SoftwareWhiteService{

	@Autowired
	private SoftwareWhiteDAO softDao;
	
	@Override
	@Transactional
	public void save(SoftwareWhite software) {
		// TODO 自动生成的方法存根
		softDao.save(software);
	}

	@Override
	@Transactional
	public SoftwareWhite getById(Long id) {
		// TODO 自动生成的方法存根
		return softDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		softDao.removeById(id);
		return true;
	}

	@Override
	public List<SoftwareWhite> getAll() {
		// TODO 自动生成的方法存根
		return softDao.findAll();
	}
}
