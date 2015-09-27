package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.HardDisk;

public interface HardDiskService {

	List<HardDisk> getList();
	void addHardDisk(HardDisk disk);
	List<HardDisk> getListByServerId(int serverid);
	void delete(long id);
}
