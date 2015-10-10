package com.cngc.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AssetDAO;
import com.cngc.pm.dao.ManufaDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.model.Asset;
import com.cngc.pm.model.Style;
import com.cngc.pm.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private StyleDAO styleDao;
	@Autowired
	private AssetDAO assetDao;
	@Autowired
	private ManufaDAO manufaDao;
	
	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapStyle() {
		List<Style> list = styleDao.getListByType(1);
		
		Map<String , String> styleMaps = new HashMap<String, String>();
		for(Style style : list) {
			styleMaps.put(String.valueOf(style.getId()), style.getName());
		}
		
		return styleMaps;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Asset> getList() {
		// TODO Auto-generated method stub
		return assetDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Asset getById(long id) {
		
		return assetDao.find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isExsitAssetNum(String assetNum) {
		if(assetDao.isExsitsAssetNum(assetNum)) return true;
		return false;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, String> getMapManufa() {
		return manufaDao.getAllMap();
	}

}
