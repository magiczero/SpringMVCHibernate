package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Software;

public interface SoftwareService {

	void add(Software software);
	
	Map<String, String> getMapAssetStyle();

	Software getById(long id);

	List<Servers> getServers(long id);

	Map<String, String> getMapManufa();
}
