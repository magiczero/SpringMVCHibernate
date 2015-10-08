package com.cngc.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.SoftwareDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Software;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.SoftwareService;
@Service
public class SoftwareServiceImpl implements SoftwareService {

	@Autowired
	private SoftwareDAO softwareDao;
	@Autowired
	private StyleDAO styleDao;

	@Override
	@Transactional
	public void add(Software software) {
		// TODO Auto-generated method stub
		softwareDao.save(software);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapAssetStyle() {
		// TODO Auto-generated method stub
		List<Style> list = styleDao.getListByType(1);
		
		Map<String , String> styleMaps = new HashMap<String, String>();
		for(Style style : list) {
			styleMaps.put(String.valueOf(style.getId()), style.getName());
		}
		
		return styleMaps;
	}

	@Override
	@Transactional(readOnly=true)
	public Software getById(long id) {
		return softwareDao.find(id);
	}
}
