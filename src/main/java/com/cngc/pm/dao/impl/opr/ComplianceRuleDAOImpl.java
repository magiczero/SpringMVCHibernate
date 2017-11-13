package com.cngc.pm.dao.impl.opr;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.dao.opr.ComplianceRuleDAO;
import com.cngc.pm.model.opr.ComplianceRule;
import com.googlecode.genericdao.search.Search;

@Repository
public class ComplianceRuleDAOImpl extends BaseDAOImpl<ComplianceRule,Long> implements ComplianceRuleDAO {

	@Override
	public List<ComplianceRule> getRuleByItem(String item) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComplianceRule.class);
		search.addFilterEqual("item", item);
		return this.search(search);
	}
}
