package com.cngc.pm.dao.impl.computer;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.computer.InspectionDataDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.InspectionData;
import com.googlecode.genericdao.search.Search;

@Repository
public class InspectionDataDAOImpl extends BaseDAOImpl<InspectionData,Long> implements InspectionDataDAO{

	@Override
	public List<InspectionData> getByTaskId(Long computerTaskId) {
		// TODO 自动生成的方法存根
		Search search = new Search(InspectionData.class);
		search.addFilterEqual("computerTaskId",computerTaskId);
		return this.search(search);
	}

	@Override
	public List<InspectionData> getData(Long computerTaskId, String itemName,boolean bgather) {
		// TODO 自动生成的方法存根
		Search search = new Search(InspectionData.class);
		search.addFilterEqual("computerTaskId",computerTaskId);
		search.addFilterEqual("itemName", itemName);
		search.addFilterEqual("gather", bgather);
		return this.search(search);
	}
	
	@Override
	public InspectionData getStatData(Long computerTaskId, String itemName) {
		// TODO 自动生成的方法存根
		Search search = new Search(InspectionData.class);
		search.addFilterEqual("computerTaskId",computerTaskId);
		search.addFilterEqual("itemName", itemName);
		search.addFilterEqual("gather", true);
		List<InspectionData> list = this.search(search);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	/*
	 * 获取指定数据
	 * @see com.cngc.pm.dao.computer.InspectionDataDAO#getData(java.util.Set, java.lang.String, boolean, java.lang.String)
	 */
	@Override
	public List<InspectionData> getData(Set<Long> ids,String itemName,boolean bCompliance,String mark,boolean bgather) {
		// TODO 自动生成的方法存根
		Search search = new Search(InspectionData.class);
		search.addFilterIn("computerTaskId", ids);
		if(itemName!=null)
			search.addFilterEqual("itemName", itemName);
		search.addFilterEqual("compliance", bCompliance);
		if(mark!=null)
			search.addFilterEqual("mark", mark);
		search.addFilterEqual("gather", bgather);
		return this.search(search);
	}

	@Override
	public List<InspectionData> getStats(List<Long> ids, String itemName, boolean bCompliance) {
		// TODO 自动生成的方法存根
		Search search = new Search(InspectionData.class);
		search.addFilterIn("computerTaskId", ids);
		search.addFilterEqual("itemName", itemName);
		search.addFilterEqual("gather", true);
		search.addFilterEqual("compliance",bCompliance);
		return this.search(search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getStats(Long taskId, String itemName,
			boolean bCompliance, String mark) {
		// TODO 自动生成的方法存根
		String sql = "select computer_task_id from cmp_inspection_data "
					+" where computer_task_id in (select id from cmp_task_computer where task_id="+taskId+")"
					+" and compliance="+(bCompliance?1:0)+" and item_name='"+itemName+"'";
		if(mark!=null&&!(mark.isEmpty()))
			sql += " and mark='"+mark+"'";
		sql += " GROUP BY computer_task_id";

		Query query = this.getSession().createSQLQuery(sql);
		List<Long> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getStatsByNotExist(Long taskId, String itemName,
			boolean bCompliance, String mark) {
		// TODO 自动生成的方法存根
		String sql = "select computer_task_id from cmp_inspection_data "
				+" where computer_task_id in (select id from cmp_task_computer where task_id="+taskId+")"
				+" and computer_task_id not in(select computer_task_id from cmp_inspection_data where compliance="+(bCompliance?1:0)+" and item_name='"+itemName+"'";
		if(mark!=null&&!(mark.isEmpty()))
			sql += " and mark='"+mark+"'";
		sql += ") GROUP BY computer_task_id";
	
		Query query = this.getSession().createSQLQuery(sql);
		List<Long> list = query.list();
		return list;
	}
	
}
