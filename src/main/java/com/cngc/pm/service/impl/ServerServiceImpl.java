package com.cngc.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ServerDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDAO serverDAO;
	@Autowired
	private StyleDAO styleDao;

	@Override
	@Transactional(readOnly=true)
	public List<Servers> getServerList() {
		// TODO Auto-generated method stub
		return serverDAO.getList();
	}

	@Override
	@Transactional
	public void addServer(Servers s) {
		// TODO Auto-generated method stub
		serverDAO.save(s);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Style> getServerStyle() {
		// TODO Auto-generated method stub
		return styleDao.getListByType(1);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapStyle() {
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
	public Servers getServerById(int id) {
		return  serverDAO.find(Long.valueOf(id));
	}

}
