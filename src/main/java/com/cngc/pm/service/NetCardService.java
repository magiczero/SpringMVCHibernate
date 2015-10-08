package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.NetworkCard;
import com.cngc.pm.model.Servers;
import com.cngc.pm.model.Style;

public interface NetCardService {

	List<NetworkCard> getListByServerid(Long id);

	Servers getServerById(long id);
	
	List<Style> getNetworkCardType();
	
	Map<String, String> getMapType();
	
	void addNetworkCard(NetworkCard netcard);

	Long  delete(Long id);
	
}
