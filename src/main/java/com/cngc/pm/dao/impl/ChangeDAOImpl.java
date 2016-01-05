package com.cngc.pm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ChangeDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.Change;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class ChangeDAOImpl extends BaseDAOImpl<Change, Long> implements ChangeDAO {

	@Autowired
	private StatsDAO statsDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getCountByStatus() {

		List list = this
				.getSession()
				.createCriteria(Change.class)
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
	public SearchResult<Change> getByIds(List<Long> ids) {
		Search search = new Search();
		search.addFilterIn("id", ids);

		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<Change> getByStatus(String status) {
		Search search = new Search();
		search.addFilterEqual("status", status);

		return this.searchAndCount(search);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.ChangeDAO#getByProcessInstance(java.util.List)
	 */
	@Override
	public SearchResult<Change> getByProcessInstance(List<String> processInstanceIds) {
		Search search = new Search();
		search.addFilterIn("processInstanceId", processInstanceIds);
		return this.searchAndCount(search);
	}

	@Override
	public SearchResult<Change> getNotFinished() {
		Search search = new Search();
		search.addFilterNotEqual("status", PropertyFileUtil.getStringValue("syscode.change.status.finished"));

		return this.searchAndCount(search);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getCountByCategory(String startTime, String endTime) {

		String sql = "SELECT COUNT(*) AS COUNT,SUBSTR(change_type,1,2) AS category FROM wk_change "
				+ " WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' "
				+ " GROUP BY SUBSTR(change_type,1,2)";

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			map.put(objs[1].toString(), objs[0].toString());
		}
		return map;
	}

	@Override
	public SearchResult<Change> search(String description, String applyUser, String engineer, String risk,
			Date startDate, Date endDate) {
		Search search = new Search();
		if (description != null) {
			if (!description.isEmpty())
				search.addFilterLike("description", "%" + description + "%");
		}
		if (applyUser != null) {
			if (!applyUser.isEmpty())
				search.addFilterEqual("applyUser", applyUser);
		}
		if (engineer != null) {
			if (!engineer.isEmpty())
				search.addFilterEqual("delegateUser", engineer);
		}
		if (risk != null)
			search.addFilterEqual("risk", risk);
		if (startDate != null)
			search.addFilterGreaterOrEqual("applyTime", startDate);
		if (endDate != null)
			search.addFilterLessOrEqual("endTime", endDate);

		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.change.status.finished"));

		return this.searchAndCount(search);
	}

	@Override
	public Map<String, Object> getStats(String column, String row, String startTime, String endTime, String status) {
		return statsDao.getStats("CHANGE", column, row, startTime, endTime, status);
	}

}
