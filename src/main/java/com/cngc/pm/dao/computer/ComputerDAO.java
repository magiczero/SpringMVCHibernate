package com.cngc.pm.dao.computer;

import java.util.Set;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.cngc.pm.model.computer.Computer;

public interface ComputerDAO extends GenericDAO<Computer,Long>{

	Computer getComputerByMac(String mac);
	Set<Computer> getComputerByIds(String ids);
	boolean removeByIds(String ids);
	boolean setDepartment(String ids,Long groupid);
}
