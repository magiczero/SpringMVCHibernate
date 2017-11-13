package com.cngc.pm.dao.opr;

import java.util.List;

import com.cngc.pm.model.opr.ComplianceRule;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ComplianceRuleDAO extends GenericDAO<ComplianceRule,Long>{

	List<ComplianceRule> getRuleByItem(String item);
}
