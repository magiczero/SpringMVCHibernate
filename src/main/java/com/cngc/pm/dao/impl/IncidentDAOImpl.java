package com.cngc.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.Incident;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class IncidentDAOImpl extends BaseDAOImpl<Incident, Long> implements IncidentDAO {

	@Autowired
	private StatsDAO statsDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getCountByStatus() {

		List list = this
				.getSession()
				.createCriteria(Incident.class)
				.setProjection(
						Projections.projectionList().add(Projections.rowCount())
								.add(Projections.groupProperty("status"))).list();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			map.put(objs[1].toString(), objs[0].toString());
		}
		return map;
	}

	@Override
	public SearchResult<Incident> getByIds(List<Long> ids) {
		Search search = new Search();
		search.addFilterIn("id", ids);

		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<Incident> getByApplyUser(String user) {
		Search search = new Search();
		search.addFilterEqual("applyUser", user);

		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<Incident> getByApplyUser(String user, Boolean bClose) {
		Search search = new Search();
		search.addFilterEqual("applyUser", user);

		if (bClose)
			search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		else
			search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));

		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<Incident> getByStatus(String status) {
		Search search = new Search();
		search.addFilterEqual("status", status);

		return this.searchAndCount(search);
	}

	/**
	 * 根据流程id获取事件列表
	 * 
	 * @param processInstanceIds
	 * @return
	 */
	@Override
	public SearchResult<Incident> getByProcessInstance(List<String> processInstanceIds) {
		Search search = new Search();
		search.addFilterIn("processInstanceId", processInstanceIds);
		return this.searchAndCount(search);
	}

//	@Override
//	public SearchResult<Incident> search(String abs, String applyUser, String engineer, String satisfaction,
//			Date startTime, Date endTime) {
//		Search search = new Search();
//		if (applyUser != null) {
//			if (!applyUser.isEmpty())
//				search.addFilterEqual("applyUser", applyUser);
//		}
//		if (engineer != null) {
//			if (!engineer.isEmpty())
//				search.addFilterEqual("currentDelegateUser", engineer);
//		}
//		if (satisfaction != null)
//			search.addFilterEqual("satisfaction", satisfaction);
//		if (startTime != null)
//			search.addFilterGreaterOrEqual("applyTime", startTime);
//		if (endTime != null)
//			search.addFilterLessOrEqual("recoverTime", endTime);
//		if (abs != null) {
//			if (!abs.isEmpty())
//				search.addFilterLike("abs", "%" + abs + "%");
//		}
//
//		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
//
//		return this.searchAndCount(search);
//	}

	@Override
	public SearchResult<Incident> getNotFinished() {
		Search search = new Search();
		search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		search.addSortAsc("status");
		search.addSortDesc("applyTime");

		return this.searchAndCount(search);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCountByCategory(String startTime, String endTime) {

		String sql = "SELECT COUNT(*) AS COUNT,SUBSTR(category,1,2) AS category FROM wk_incident "
				+ " WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' "
				+ " GROUP BY SUBSTR(category,1,2)";

		Query query = this.getSession().createSQLQuery(sql);
		List<Object> list = query.list();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			map.put(objs[1].toString(), objs[0].toString());
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.IncidentDAO#getStats(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getStats(String column, String row, String startTime, String endTime, String status) {

		return statsDao.getStats("INCIDENT",column, row, startTime, endTime, status);

	}

}
