package com.cngc.pm.dao;

import java.util.Map;

public interface StatsDAO {

	/**
	 * 获取统计信息
	 * 
	 * @param table
	 * @param column
	 * @param row
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	Map<String, Object> getStats(String table, String column, String row, String startTime, String endTime,
			String status);
}
