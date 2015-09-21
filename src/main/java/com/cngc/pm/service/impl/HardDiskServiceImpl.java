package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.HardDiskDAO;
import com.cngc.pm.model.HardDisk;
import com.cngc.pm.service.HardDiskService;

@Service
public class HardDiskServiceImpl implements HardDiskService {

	@Autowired
	private HardDiskDAO diskDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<HardDisk> getList() {
		// TODO Auto-generated method stub
		//return diskDao.getList();
		return diskDao.findAll();
	}

	@Override
	@Transactional
	public void addHardDisk(HardDisk disk) {
		// TODO Auto-generated method stub
		diskDao.save(disk);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HardDisk> getListByServerId(int serverid) {

		
		return diskDao.getListByServer(serverid);
	}

}
