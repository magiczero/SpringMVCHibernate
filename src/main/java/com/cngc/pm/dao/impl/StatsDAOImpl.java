package com.cngc.pm.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.dao.cms.CategoryDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ItilRelation;
import com.cngc.pm.model.SysCode;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;

@Repository
public class StatsDAOImpl extends BaseDAOImpl<Object, Long> implements StatsDAO {

	@Autowired
	private SysCodeDAO syscodeDao;
	@Resource
	private UserService userService;
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private ItilRelationDAO itilRelationDao;
	@Autowired
	private ChangeItemDAO changeItemDao;
	@Autowired
	private CategoryDAO categoryDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cngc.pm.dao.StatsDAO#getStats(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public Map<String, Object> getStats(String table, String column, String row, String startTime, String endTime,
			String status) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> colmap = new HashMap<String, Object>();
		Map<String, Object> rowmap = new HashMap<String, Object>();

		List collist = null, rowlist = null;
		String columnPrefix = null, columnName = null, rowPrefix = null, rowName = null;
		// 读取column分类
		if (column != null) {
			int icol = column.indexOf('_');
			if (icol >= 0) {
				columnPrefix = column.substring(0, icol);
				columnName = column.substring(icol + 1);
				colmap = getCategoryList(columnPrefix, columnName);
				result.put("column", colmap);
				columnName = getCategoryName(columnPrefix, columnName);
			}
		}
		// 读取row分类
		if (row != null) {
			int irow = row.indexOf('_');
			if (irow >= 0) {
				rowPrefix = row.substring(0, irow);
				rowName = row.substring(irow + 1);
				rowmap = getCategoryList(rowPrefix, rowName);
				result.put("row", rowmap);
				rowName = getCategoryName(rowPrefix, rowName);
			}
		}
		// 开始统计
		if (row != null) {
			// 二维统计
			switch (table) {
			case "INCIDENT":
				getStatsForIncident2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "CHANGE":
				getStatsForChange2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "CMS":
				getStatsForCi2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "KNOWLEDGE":
				getStatsForKnowledge2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "LEADERTASK":
				getStatsForLeaderTask2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "INSPECTION":
				getStatsForInspection2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "UPDATE":
				getStatsForUpdate2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			case "SECJOB":
				getStatsForSecjob2(rowName, columnName, rowmap, startTime, endTime, status, result);
				break;
			}

		} else {
			// 一维统计
			switch (table) {
			case "INCIDENT":
				getStatsForIncident1(columnName, startTime, endTime, status, result);
				break;
			case "CHANGE":
				getStatsForChange1(columnName, startTime, endTime, status, result);
				break;
			case "CMS":
				getStatsForCi1(columnName, status, result);
				break;
			case "KNOWLEDGE":
				getStatsForKnowledge1(columnName, startTime, endTime, status, result);
				break;
			case "LEADERTASK":
				getStatsForLeaderTask1(columnName, startTime, endTime, status, result);
				break;
			case "INSPECTION":
				getStatsForInspection1(columnName, startTime, endTime, status, result);
				break;
			case "UPDATE":
				getStatsForUpdate1(columnName, startTime, endTime, status, result);
				break;
			case "SECJOB":
				getStatsForSecjob1(columnName, startTime, endTime, status, result);
				break;
			}

		}

		return result;
	}

	/**
	 * 获取事件统计二维表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForIncident2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_incident ";
			if (rowName.equals("id"))
				sql += " where " + rowName + " in(select primary_id from wk_itil_relation where secondary_id="
						+ entry.getKey() + " and relation_type='INCIDENT_CI')";
			else
				sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.incident.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取事件统计一维表
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForIncident1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		String sql = "";
		if (columnName.equals("id")) {
			sql = "SELECT COUNT(*) AS COUNT,R.secondary_id AS COLUMNID "
					+ " FROM wk_incident i RIGHT JOIN wk_itil_relation r ON i.id=r.primary_id AND r.relation_type='INCIDENT_CI' "
					+ " WHERE DATE(i.apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.incident.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += " GROUP BY R.SECONDARY_ID ";
		} else {
			sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_incident "
					+ "  WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.incident.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += "  GROUP BY " + columnName;
			;
		}

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取变更二维统计表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForChange2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_change ";
			if (rowName.equals("id"))
				sql += " where " + rowName + " in(select change_id from wk_change_item where ci_id=" + entry.getKey()
						+ ") ";
			else
				sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.change.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取变更一维统计表
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForChange1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		if (columnName.equals("id")) {
			sql = "SELECT COUNT(*) AS COUNT,R.change_id AS COLUMNID "
					+ " FROM wk_change i RIGHT JOIN wk_change_item r ON i.id=r.change_id "
					+ " WHERE DATE(i.apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.change.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += " GROUP BY R.CHANGE_ID ";
		} else {
			sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_change "
					+ "  WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
				// 统计工单时仅统计正常关闭的工单
				if(status.equals(PropertyFileUtil.getStringValue("syscode.change.status.finished")))
					sql += " AND endbyuser=TRUE ";
			}
			sql += "  GROUP BY " + columnName;
			;
		}

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取配置项一维统计
	 * 
	 * @param columnName
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForCi1(String columnName, String status, Map<String, Object> result) {
		// 一维统计
		String sql = "";

		sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM cms_ci ";
		if (status != null) {
			if (!status.equals("00"))
				sql += " WHERE status_='" + status + "' ";
		}
		sql += "  GROUP BY " + columnName;

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取配置项二维统计信息
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForCi2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM cms_ci ";
			if (rowName.equals("id"))
				sql += " where " + rowName + " in(select primary_id from wk_itil_relation where secondary_id="
						+ entry.getKey() + " and relation_type='INCIDENT_CI')";
			else
				sql += " where " + rowName + "='" + entry.getKey() + "'";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
			}
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取知识统计二维统计
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForKnowledge2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_knowledge ";
			if (rowName.equals("id"))
				sql += " where " + rowName + " in (select secondary_id from wk_itil_relation where primary_id="
						+ entry.getKey() + " and relation_type='INCIDENT_KNOWLEDGE')";
			else
				sql += " where " + rowName + "='" + entry.getKey() + "'";
			if (startTime != null)
				sql += " AND DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
			}
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取领导交办二维统计表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForLeaderTask2(String rowName, String columnName, Map<String, Object> rowmap,
			String startTime, String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_task ";
			sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE ";
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取知识一维统计
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForKnowledge1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		if (columnName.equals("id")) {
			sql = "SELECT COUNT(*) AS COUNT,R.primary_id AS COLUMNID "
					+ " FROM wk_knowledge i RIGHT JOIN wk_itil_relation r ON i.id=r.secondary_id AND r.relation_type='INCIDENT_KNOWLEDGE' ";
			if (startTime != null)
				sql += " WHERE DATE(i.apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
			}
			sql += " GROUP BY R.primary_id ";
		} else {
			sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_knowledge ";
			if (startTime != null)
				sql += "  WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			if (status != null) {
				if (!status.equals("00"))
					sql += " AND status_='" + status + "' ";
			}
			sql += "  GROUP BY " + columnName;
			;
		}

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取领导交办任务一维统计
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForLeaderTask1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_task "
				+ "  WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
		sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE ";
		sql += "  GROUP BY " + columnName;
		;

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取巡检一维统计表
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForInspection1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_inspection "
				+ "  WHERE DATE(created_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
		sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE ";
		sql += "  GROUP BY " + columnName;
		;

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取巡检统计二维表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForInspection2(String rowName, String columnName, Map<String, Object> rowmap,
			String startTime, String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_inspection ";
			sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(created_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE  ";
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取升级统计一维表
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForUpdate1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_update "
				+ "  WHERE DATE(created_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
		sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE  ";
		sql += "  GROUP BY " + columnName;
		;

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);
	}

	/**
	 * 获取升级统计二维表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForUpdate2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_update ";
			sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(created_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE ";
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 获取三员工作一维统计表
	 * 
	 * @param columnName
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForSecjob1(String columnName, String startTime, String endTime, String status,
			Map<String, Object> result) {
		// 一维统计
		String sql = "";
		sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_sec_job "
				+ "  WHERE DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
		sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE  ";
		sql += "  GROUP BY " + columnName;
		;

		Query query = this.getSession().createSQLQuery(sql);
		List list = query.list();
		Map<String, Object> counts = new HashMap<String, Object>();
		for (Object element : list) {
			Object[] objs = (Object[]) element;
			if (objs[1] != null)
				counts.put(objs[1].toString(), objs[0].toString());
		}

		result.put("counts", counts);

	}

	/**
	 * 获取三员工作二维统计表
	 * 
	 * @param rowName
	 * @param columnName
	 * @param rowmap
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param result
	 */
	@SuppressWarnings("rawtypes")
	private void getStatsForSecjob2(String rowName, String columnName, Map<String, Object> rowmap, String startTime,
			String endTime, String status, Map<String, Object> result) {
		// 二维统计
		for (Map.Entry<String, Object> entry : rowmap.entrySet()) {
			String sql = "SELECT COUNT(*) AS COUNT," + columnName + " AS COLUMNID FROM wk_sec_job ";
			sql += " where " + rowName + "='" + entry.getKey() + "'";
			sql += " AND DATE(apply_time) BETWEEN '" + startTime + "'  AND '" + endTime + "' ";
			sql += " AND execution_time IS NOT NULL AND endbyuser=TRUE ";
			sql += " GROUP BY " + columnName;
			;
			Query query = this.getSession().createSQLQuery(sql);
			List list = query.list();
			Map<String, Object> counts = new HashMap<String, Object>();
			for (Object element : list) {
				Object[] objs = (Object[]) element;
				if (objs[1] != null)
					counts.put(objs[1].toString(), objs[0].toString());
			}

			result.put(entry.getKey(), counts);
		}
	}

	/**
	 * 根据传入的前缀和字段名获取数据库字段名
	 * 
	 * @param prefix
	 * @param name
	 * @return
	 */
	private String getCategoryName(String prefix, String name) {
		switch (prefix) {
		case "CODE":
			switch (name) {
			case "INCIDENT_STATUS":
			case "CHANGE_STATUS":
			case "CI_STATUS":
			case "KNOWLEDGE_STATUS":
			case "LEADERTASK_STATUS":
			case "INSPECTION_STATUS":
			case "UPDATE_STATUS":
			case "SECJOB_STATUS":
				name = "status_";
				break;
			case "DELETE":
				name = "delete_status";
				break;
			case "REVIEW":
				name = "review_status";
				break;
			case "INCIDENT_CATEGORY":
				name = "category";
				break;
			case "CI_CATEGORY":
				name = "category_code";
				break;
			case "KNOWLEDGE_CATEGORY":
				name = "category";
				break;
			}
			break;
		case "USER":
			switch (name) {
			case "INCIDENT_ENGINEER":
				name = "current_delegate_user";
				break;
			case "CHANGE_ENGINEER":
				name = "delegate_user";
				break;
			case "KNOWLEDGE_ENGINEER":
				name = "apply_user";
				break;
			case "CI_ENGINEER":
				name = "user_in_maintenance";
				break;
			case "APPLY":
				name = "apply_user";
				break;
			case "LEADERTASK_ENGINEER":
				name = "to_user";
				break;
			case "LEADERTASK_APPLY":
				name = "from_user";
				break;
			case "INSPECTION_ENGINEER":
				name = "execution_user";
				break;
			case "UPDATE_ENGINEER":
				name = "userid";
				break;
			case "SECJOB_ENGINEER":
				name = "user_id";
				break;
			}
			break;
		case "CI":
			switch (name) {
			case "INCIDENT_ASSET":
			case "CHANGE_ASSET":
				name = "id";
				break;
			}
		case "DATE":
			switch (name) {
			case "MONTH":
			case "SECJOB_MONTH":
				name = "MONTH(apply_time)";
				break;
			case "INSPECTION_MONTH":
			case "UPDATE_MONTH":
				name = "MONTH(created_time)";
				break;
			case "YEAR":
			case "SECJOB_YEAR":
				name = "YEAR(apply_time)";
				break;
			case "INSPECTION_YEAR":
			case "UPDATE_YEAR":
				name = "YEAR(created_time)";
				break;
			}
			break;
		}
		return name;
	}

	/**
	 * 根据前缀和字段名获取列表信息
	 * 
	 * @param prefix
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Object> getCategoryList(String prefix, String name) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List list = null;
		switch (prefix) {
		case "CODE": // 数据字典
			switch (name) {
			case "INCIDENT_STATUS":
				list = syscodeDao.getCodeByType("INCIDENT_STATUS").getResult();
				break;
			case "SOURCE":
				list = syscodeDao.getCodeByType("INCIDENT_SOURCE").getResult();
				break;
			case "INCIDENT_CATEGORY":
				list = syscodeDao.getCodeByType("INCIDENT_CATEGORY").getResult();
				break;
			case "SATISFACTION":
				list = syscodeDao.getCodeByType("INCIDENT_SATISFACTION").getResult();
				break;
			case "CHANGE_STATUS":
				list = syscodeDao.getCodeByType("CHANGE_STATUS").getResult();
				break;
			case "RISK":
				list = syscodeDao.getCodeByType("CHANGE_RISK").getResult();
				break;
			case "CI_STATUS":
				list = syscodeDao.getCodeByType("CI_STATUS").getResult();
				break;
			case "DELETE":
				list = syscodeDao.getCodeByType("CI_DELETE_STATUS").getResult();
				break;
			case "REVIEW":
				list = syscodeDao.getCodeByType("CI_REVIEW_STATUS").getResult();
				break;
			case "CI_CATEGORY":
				list = categoryDao.getCategoryOrderByCode();
				break;
			case "KNOWLEDGE_STATUS":
				list = syscodeDao.getCodeByType("KNOWLEDGE_STATUS").getResult();
				break;
			case "KNOWLEDGE_CATEGORY":
				list = syscodeDao.getCodeByType("INCIDENT_CATEGORY").getResult();
				break;
			default:
				list = syscodeDao.getCodeByType(name).getResult();
			}
			if (name.equals("CI_CATEGORY")) {
				for (Object code : list)
					map.put(((Category) code).getCategoryCode(), ((Category) code).getCategoryName());
			} else {
				for (Object code : list)
					map.put(((SysCode) code).getCode(), ((SysCode) code).getCodeName());
			}
			break;
		case "USER": // 用户
			switch (name) {
			case "INCIDENT_ENGINEER": // 工程师
			case "CHANGE_ENGINEER":
			case "CI_ENGINEER":
			case "KNOWLEDGE_ENGINEER":
			case "LEADERTASK_ENGINEER":
			case "INSPECTION_ENGINEER":
			case "UPDATE_ENGINEER":
			case "SECJOB_ENGINEER":
				list = userService.getEngineer();
				break;
			case "APPLY": // 申请用户
				list = userService.getAll();
				break;
			}
			for (Object code : list)
				map.put(((SysUser) code).getUsername(), ((SysUser) code).getName());
			break;
		case "CI": // 配置项
			switch (name) {
			case "INCIDENT_ASSET":
				List<ItilRelation> relations = itilRelationDao.getByType("INCIDENT_CI").getResult();
				List<Long> ids = new ArrayList<Long>();
				for (ItilRelation relation : relations)
					ids.add(relation.getSecondaryId());
				list = ciDao.getByIds(ids).getResult();
				for (Object code : list)
					map.put(((Ci) code).getId().toString(), ((Ci) code).getName());
				break;
			case "CHANGE_ASSET":
				List<Long> ciids = new ArrayList<Long>();
				List<ChangeItem> items = changeItemDao.findAll();
				for (ChangeItem item : items)
					ciids.add(item.getCiId());
				list = ciDao.getByIds(ciids).getResult();
				for (Object code : list)
					map.put(((Ci) code).getId().toString(), ((Ci) code).getName());
				break;
			}
			break;
		case "DATE": // 日期
			switch (name) {
			case "MONTH": // 月份
			case "INSPECTION_MONTH":
			case "UPDATE_MONTH":
			case "SECJOB_MONTH":
				for (Integer i = 1; i <= 12; i++)
					map.put(i.toString(), i.toString() + "月");
				break;
			case "YEAR": // 年度
			case "INSPECTION_YEAR":
			case "UPDATE_YEAR":
			case "SECJOB_YEAR":
				Calendar now = Calendar.getInstance();
				for (Integer i = PropertyFileUtil.getIntValue("system.running.year"); i <= now.get(Calendar.YEAR); i++)
					map.put(i.toString(), i.toString() + "年");
				break;
			}
			break;
		}

		return map;
	}
}
