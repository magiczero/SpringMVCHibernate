package com.cngc.pm.dao.impl.computer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.computer.RsComputerInfoDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.RsComputerInfo;
import com.googlecode.genericdao.search.Search;

@Repository
public class RsComputerInfoDAOImpl extends BaseDAOImpl<RsComputerInfo,Long> implements RsComputerInfoDAO{

	@Override
	public List<RsComputerInfo> getByTaskId(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Search search = new Search(RsComputerInfo.class);
		search.addFilterEqual("computerTaskId", computerTaskId);
		return this.search(search);
	}
	
}
