package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.service.MoudleService;

@Service
public class MoudleServiceImpl implements MoudleService {

	@Autowired
	private MoudleDAO moudleDao;

	@Override
	@Transactional(readOnly=true)
	public List<Moudle> getAllMenu() {
		// TODO Auto-generated method stub
		return moudleDao.getAllMenus();
	}

	@Override
	@Transactional
	public void save(Moudle moudle) {
		// TODO Auto-generated method stub
		moudleDao.save(moudle);
	}

	@Override
	@Transactional
	public boolean enableStatus(long id) {
		// TODO Auto-generated method stub
		Moudle m = moudleDao.find(id);
		boolean enable = m.isEnable();
		if(enable) {
			m.setEnable(false);
		} else {
			m.setEnable(true);
		}
		
		Moudle m2 = moudleDao.update(m);
		
		if(m2.isEnable() == enable)
			return false;
		
		return true;
	}
}
