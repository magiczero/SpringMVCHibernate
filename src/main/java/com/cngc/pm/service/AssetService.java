package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Asset;

public interface AssetService {

	Map<String, String> getMapStyle();
	
	List<Asset> getList();
	
	Asset getById(long id);
	
	boolean isExsitAssetNum(String assetNum);

	Map<String, String> getMapManufa();
}
