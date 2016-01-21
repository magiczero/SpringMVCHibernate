package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.service.MoudleService;

@Service
public class MoudleServiceImpl implements MoudleService {

	@Autowired
	private MoudleDAO moudleDao;
	@Autowired
	private RecordsDAO recordsDao;

	@Override
	@Transactional(readOnly=true)
	public List<Moudle> getAllMenu() {
		// TODO Auto-generated method stub
		return moudleDao.getAllMenus();
	}

	@Override
	@Transactional
	public void save(Moudle moudle, String username) {
		// TODO Auto-generated method stub
		moudleDao.save(moudle);
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.menu);
		record.setDesc("新建了菜单，详细信息，菜单名称：[" + moudle.getName() + "]，上级菜单：[" + moudle.getParent()==null?moudle.getParent().getName():"无"+"]，路径：["+moudle.getUrl()+"]");
		recordsDao.save(record);
	}

	@Override
	@Transactional
	public boolean enableStatus(long id, String username) {
		// TODO Auto-generated method stub
		Moudle m = moudleDao.find(id);
		boolean enable = m.isEnable();
		if(enable) {
			m.setEnable(false);
		} else {
			m.setEnable(true);
		}
		
		Moudle m2 = moudleDao.update(m);
		
		String enableStr="启用";
		
		if(m2.isEnable() == enable) {
			enableStr = "停用";
			return false;
		}
		
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.menu);
		record.setDesc(enableStr+"了菜单，详细信息，菜单名称：[" + m2.getName() + "]，菜单id：[" + m2.getId() + "]");
		recordsDao.save(record);
		
		return true;
	}
}
