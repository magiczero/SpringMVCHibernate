package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.HardDisk;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface HardDiskDAO extends GenericDAO<HardDisk, Long> {

	/**
	 * 查询所有的硬盘，此方法查询速度快
	 * @return
	 */
	List<HardDisk> getList();

	List<HardDisk> getListByServer(int serverid);
}
