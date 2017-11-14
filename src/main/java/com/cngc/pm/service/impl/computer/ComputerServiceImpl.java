package com.cngc.pm.service.impl.computer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.GroupDAO;
import com.cngc.pm.dao.computer.ComputerDAO;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.service.computer.ComputerService;
import com.googlecode.genericdao.search.Search;

@Service
public class ComputerServiceImpl implements ComputerService{
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private GroupDAO	groupDao;
	
	@Override
	@Transactional
	public void save(Computer computer) {
		// TODO 自动生成的方法存根
		computerDao.save(computer);
	}

	@Override
	@Transactional
	public Computer getById(Long id) {
		// TODO 自动生成的方法存根
		return computerDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		computerDao.removeById(id);
		return true;
	}

	@Override
	public boolean delByIds(String ids) {
		// TODO 自动生成的方法存根
		computerDao.removeByIds(ids);
		return true;
	}

	@Override
	public List<Computer> getAll() {
		// TODO 自动生成的方法存根
		return computerDao.findAll();
	}

	@Override
	public List<Computer> getByGroup(Long groupid) {
		// TODO 自动生成的方法存根
		Search search = new Search(Computer.class);
		Set<Group> groups = new HashSet<Group>();
		Group group = groupDao.find(groupid);
		groups.add(group);
		if(group.getChild()!=null)
			groups.addAll(group.getChild());
		for(Iterator<Group> it=group.getChild().iterator();it.hasNext();)
		{
			groups.addAll(it.next().getChild());
		}
		search.addFilterIn("group", groups);
		return computerDao.search(search);
	}

	@Override
	public Computer getComputerByMac(String mac) {
		// TODO 自动生成的方法存根
		return computerDao.getComputerByMac(mac);
	}

	@Override
	public Set<Computer> getByIds(String ids) {
		// TODO 自动生成的方法存根
		return computerDao.getComputerByIds(ids);
	}

	@Override
	public boolean setDepartment(String ids, Long groupid) {
		// TODO 自动生成的方法存根
		computerDao.setDepartment(ids, groupid);
		return false;
	}

	@Override
	public List<Computer> getRegistedAll() {
		// TODO 自动生成的方法存根
		Search search = new Search(Computer.class);
		search.addFilterNotNull("group");
		return computerDao.search(search);
	}
	
	@Override
	public List<Computer> getNotRegistedAll() {
		// TODO 自动生成的方法存根
		Search search = new Search(Computer.class);
		search.addFilterNull("group");
		return computerDao.search(search);
	}

}
