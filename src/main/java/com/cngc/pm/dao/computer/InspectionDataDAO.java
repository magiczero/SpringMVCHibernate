package com.cngc.pm.dao.computer;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.computer.InspectionData;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface InspectionDataDAO extends GenericDAO<InspectionData,Long>{
	List<InspectionData> getByTaskId(Long computerTaskId);
	List<InspectionData> getData(Long computerTaskId,String itemName,boolean bgather);
	/*
	 * 获取统计记录
	 */
	InspectionData getStatData(Long computerTaskId,String itemName);
	/*
	 * 获取数据
	 */
	List<InspectionData> getData(Set<Long> ids,String itemName,boolean bCompliance,String mark,boolean bgather);
	/*
	 * 获取统计信息
	 */
	List<Long> getStats(Long taskId,String itemName,boolean bCompliance,String mark);
	/*
	 * 获取统计信息
	 */
	List<InspectionData> getStats(List<Long> ids,String itemName,boolean bCompliance);
	//统计不存在如下记录
	List<Long> getStatsByNotExist(Long taskId,String itemName,boolean bCompliance,String mark);
}
