package com.cngc.pm.service.impl.opr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.opr.ComplianceRuleDAO;
import com.cngc.pm.model.opr.ComplianceRule;
import com.cngc.pm.service.opr.ComplianceRuleService;

@Service
public class ComplianceRuleServiceImpl implements ComplianceRuleService {

	@Autowired
	private ComplianceRuleDAO ruleDao;
	
	@Override
	@Transactional
	public void save(ComplianceRule rule) {
		// TODO 自动生成的方法存根
		ruleDao.save(rule);
	}

	@Override
	@Transactional
	public ComplianceRule getById(Long id) {
		// TODO 自动生成的方法存根
		return ruleDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		// TODO 自动生成的方法存根
		ruleDao.removeById(id);
		return true;
	}

	@Override
	public List<ComplianceRule> getAll() {
		// TODO 自动生成的方法存根
		return ruleDao.findAll();
	}

}
