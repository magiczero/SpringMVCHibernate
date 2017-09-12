package com.cngc.pm.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
//	@Autowired
//	private UserDAO userDao;
//	@Autowired
//	private SysCodeDAO codeDao;

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

	@SuppressWarnings("unchecked")
	@Override
	public SearchResult<Incident> searchByUnit(String abs, String unit, String engineer, String satisfaction,
			Date startDate, Date endDate, int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		SearchResult<Incident> sr = new SearchResult<>();
		
		String hql = "select in1.abstract, sc.code_name , su.name, in1.current_delegate_user,in1.satisfaction, in1.apply_time, in1.recover_time, in1.id from wk_incident as in1 left join sys_users as su on in1.apply_user=su.username LEFT JOIN sys_code sc ON in1.priority = sc.code_ where in1.status_=:status and su.group_id like :groupid AND sc.type_='PRIORITY'";
		if (engineer != null && !engineer.isEmpty()) {
			hql += " and in1.current_delegate_user=:engineer";
		}
		if (satisfaction != null)
			hql += " and in1.satisfaction=:satisfaction";
		if (startDate != null)
			hql += " and in1.apply_time>=:startDate";
		if (endDate != null)
			hql += " and in1.recover_time<=:endDate";
		if (abs != null && !abs.isEmpty()) {
			hql += " and in.abstract like :abs";
				//search.addFilterLike("abs", "%" + abs + "%");
		}
		
		hql += " order by in1.apply_time desc";
//		System.out.println(hql);
		Query query = this.getSession().createSQLQuery(hql);
		
		query.setParameter("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		query.setParameter("groupid", unit+"%");
		if (engineer != null && !engineer.isEmpty()) 
			query.setParameter("engineer", engineer);
		
		if (satisfaction != null)
			query.setParameter("satisfaction", satisfaction);
		
		if (startDate != null)
			query.setParameter("startDate", startDate);
		
		if (endDate != null)
			query.setParameter("endDate", endDate);
		
		if (abs != null && !abs.isEmpty()) 
			query.setParameter("abs", "%" + abs + "%");
		
		sr.setTotalCount(query.list().size());
//		System.out.println(query.list().size());
		hql += " limit " + iDisplayStart + "," + iDisplayLength;
//		System.out.println(hql);
		Query query1 = this.getSession().createSQLQuery(hql);
		
		query1.setParameter("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		query1.setParameter("groupid", unit+"%");
		if (engineer != null && !engineer.isEmpty()) 
			query1.setParameter("engineer", engineer);
		
		if (satisfaction != null)
			query1.setParameter("satisfaction", satisfaction);
		
		if (startDate != null)
			query1.setParameter("startDate", startDate);
		
		if (endDate != null)
			query1.setParameter("endDate", endDate);
		
		if (abs != null && !abs.isEmpty()) 
			query1.setParameter("abs", "%" + abs + "%");
//		List<Object[]> list0 = (List<Object[]>)(query1.list());
//		
//		List<Incident> incidentList = new ArrayList<>();
//		List<Object[]> list = (List<Object[]>)(query.list());
//		for (Object[] objs : list) {
//			for(Object obj : objs) {
//				if(obj instanceof Incident) {
//					incidentList.add((Incident)obj);
//				}
//			}
//		}
		List<Incident> incidentList = new ArrayList<>();
		List<Object[]> objectsList = query1.list();
		for(Object[] objs : objectsList) {
			Incident in = new Incident();
			
			in.setAbs((String)objs[0]);
			in.setPriorityName((String)objs[1]);
			in.setApplyUserName((String)objs[2]);
			
			//这个优化 不知道怎么样
			String sql = "select su.name, sc.code_name from wk_incident as in1 left join sys_users as su on in1.current_delegate_user=su.username LEFT JOIN sys_code sc ON in1.satisfaction = sc.code_ AND sc.type_='INCIDENT_SATISFACTION' where in1.id=:id";
			
			Query query2 = this.getSession().createSQLQuery(sql);
			query2.setParameter("id", (BigInteger)objs[7]);
			
			List<Object[]> objectsList1 = query2.list();
			Object[] objs1 = objectsList1.get(0);
			in.setCurrentDelegateUser((String)objs1[0]);
			in.setSatisfactionName((String)objs1[1]);
			
			in.setApplyTime((Date)objs[5]);
			in.setRecoverTime((Date)objs[6]);
//			for(int i=0; i<objs.length; i++) {
//				switch(i) {
//				case 0 :
//					in.setAbs((String)objs[i]);
//					break;
//				case 1:
//					in.setPriorityName((String)objs[i]);
//					break;
//				case 2:
//					in.setApplyUserName((String)objs[i]);
//					break;
//				case 3:
//					in.setCurrentDelegateUser(userDao.getUserByUserName((String)objs[i]).getUsername());
//					break;
//				case 4:
//					in.setSatisfactionName(codeDao.getCode((String)objs[i], "INCIDENT_SATISFACTION").getCodeName());
//					break;
//				case 5:
//					in.setApplyTime((Date)objs[i]);
//					break;
//				case 6:
//					in.setRecoverTime((Date)objs[i]);
//					break;
//				}
//					
//			}
			incidentList.add(in);
		}
//		List<Incident> incidentList = query1.list();
		
		sr.setResult(incidentList);
		return sr;
	}

}
