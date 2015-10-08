package com.cngc.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.NetWorkDAO;
import com.cngc.pm.dao.ServerDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.NetworkCard;
import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.NetCardService;

@Service
public class NetCardServiceImpl implements NetCardService {

	@Autowired
	private NetWorkDAO netcardDao;
	@Autowired
	private ServerDAO serverDao;
	@Autowired
	private StyleDAO styleDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<NetworkCard> getListByServerid(Long id) {
		// TODO Auto-generated method stub
		return netcardDao.getListByServerid(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Servers getServerById(long id) {
		// TODO Auto-generated method stub
		return serverDao.find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Style> getNetworkCardType() {
		// TODO Auto-generated method stub
		return styleDao.getListByType(5);
	}

	@Override
	@Transactional
	public void addNetworkCard(NetworkCard netcard) {
		// TODO Auto-generated method stub
		netcardDao.save(netcard);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapType() {
		// TODO Auto-generated method stub
		List<Style> list = styleDao.getListByType(5);
		
		Map<String , String> map = new HashMap<String, String>();
		
		for(Style style : list) {
			map.put(String.valueOf(style.getId()), style.getName());
		}
		return map;
	}

	@Override
	@Transactional
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		NetworkCard netcard = netcardDao.find(id);
		Long serverid = netcard.getServer().getId();
		netcardDao.remove(netcard);
		return serverid;
	}

}
