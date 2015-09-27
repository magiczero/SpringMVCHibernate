package com.cngc.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ServerDAO;
import com.cngc.pm.dao.ServerSoftDAO;
import com.cngc.pm.dao.SoftwareDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.ServerSoftware;
import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Software;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDAO serverDAO;
	@Autowired
	private StyleDAO styleDao;
	@Autowired
	private SoftwareDAO softwareDao;
	@Autowired
	private ServerSoftDAO serverSoftDao;

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
	public Servers getServerById(Long id) {
		return  serverDAO.find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getAllMapSoftware() {
		// TODO Auto-generated method stub
		List<Software> list = softwareDao.findAll();
		
		Map<String , String> maps = new HashMap<String, String>();
		
		for(Software s : list) {
			maps.put(s.getId().toString(), s.getName()+"-" + s.getVersions());
		}
		
		return maps;
	}

	@Override
	@Transactional
	public void addServerSoftware(ServerSoftware s) {
		// TODO Auto-generated method stub
		serverSoftDao.save(s);
	}

	@Override
	@Transactional
	public List<ServerSoftware> getSoftwares(long serverid) {
		// TODO Auto-generated method stub
		return serverSoftDao.getListByServer(serverid);
	}

}
