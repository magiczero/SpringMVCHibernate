package com.cngc.pm.service.computer;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.computer.Computer;

public interface ComputerService {

	void save(Computer computer);
	Computer getById(Long id);
	Computer getComputerByMac(String mac);
	boolean delById(Long id);
	boolean delByIds(String ids);
	List<Computer> getAll();
	List<Computer> getByGroup(Long groupid);
	Set<Computer> getByIds(String ids);
	boolean setDepartment(String ids,Long groupid);
	List<Computer> getRegistedAll();
	List<Computer> getNotRegistedAll();
}
