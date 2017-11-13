package com.cngc.pm.service.impl.computer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.InspectionTargetDAO;
import com.cngc.pm.model.computer.InspectionTarget;
import com.cngc.pm.service.computer.InspectionTargetService;

@Service
public class InspectionTargetServiceImpl implements InspectionTargetService{

	@Autowired
	private InspectionTargetDAO targetDao;
	
	@Override
	@Transactional
	public void save(InspectionTarget inspectionTarget) {
		// TODO 自动生成的方法存根
		targetDao.save(inspectionTarget);
	}

	@Override
	@Transactional
	public boolean delById(Long targetId) {
		// TODO 自动生成的方法存根
		targetDao.removeById(targetId);
		return true;
	}

	@Override
	@Transactional
	public InspectionTarget getById(Long targetId) {
		// TODO 自动生成的方法存根
		return targetDao.find(targetId);
	}

	@Override
	@Transactional
	public List<InspectionTarget> getAll() {
		// TODO 自动生成的方法存根
		return targetDao.findAll();
	}

	@Override
	public String getJSON() {
		// TODO 自动生成的方法存根
		return null;
	}

}
