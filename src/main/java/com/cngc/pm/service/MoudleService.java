package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Moudle;

public interface MoudleService {

	List<Moudle> getAllMenu();

	void save(Moudle moudle, String username);

	boolean enableStatus(long id, String username);

}
