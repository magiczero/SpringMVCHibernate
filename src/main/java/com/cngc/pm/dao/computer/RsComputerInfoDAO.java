package com.cngc.pm.dao.computer;

import java.util.List;

import com.cngc.pm.model.computer.RsComputerInfo;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface RsComputerInfoDAO extends GenericDAO<RsComputerInfo,Long>{
	List<RsComputerInfo> getByTaskId(Long computerTaskId);
}
