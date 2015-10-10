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

	/**
	 * 删除服务器与软件间的关联
	 * @param serversoftid		服务器软件id
	 */
	void deleteSoftware(Long serversoftid);
	
	/**
	 * 服务器上是否已经存在此软件
	 * @param serverid
	 * @param softwareid
	 * @return 
	 */
	boolean isSoftware(Long serverid, Long softwareid);

	Map<String, String> getMapManufa();

}
