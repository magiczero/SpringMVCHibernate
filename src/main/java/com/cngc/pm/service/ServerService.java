package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.ServerSoftware;
import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Style;

public interface ServerService {
	List<Servers> getServerList();
	
	void addServer(Servers s);
	
	List<Style> getServerStyle();
	
	Map<String, String> getMapStyle();
	
	Servers getServerById(Long id);

	Map<String, String> getAllMapSoftware();

	void addServerSoftware(ServerSoftware s);

	List<ServerSoftware> getSoftwares(long serverid);

}
