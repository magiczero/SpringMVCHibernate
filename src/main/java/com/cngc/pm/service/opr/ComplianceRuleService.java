package com.cngc.pm.service.opr;

import java.util.List;

import com.cngc.pm.model.opr.ComplianceRule;

public interface ComplianceRuleService {

	void save(ComplianceRule rule);
	ComplianceRule getById(Long id);
	boolean delById(Long id);
	List<ComplianceRule> getAll();
}
