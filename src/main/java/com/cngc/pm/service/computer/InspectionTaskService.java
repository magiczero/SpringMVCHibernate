package com.cngc.pm.service.computer;

import java.util.List;

import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionData;
import com.cngc.pm.model.computer.InspectionTask;


public interface InspectionTaskService {

	void save(InspectionTask inspectionTask,String userid) throws Exception;
	boolean delById(Long taskId);
	InspectionTask getById(Long taskId);
	List<InspectionTask> getAll();
	List<ComputerTask> getNewTaskByComputer(Computer computer);
	List<ComputerTask> getByTask(InspectionTask task);
	List<ComputerTask> getByIds(List<Long> ids);
	//获取检查任务对应的已解析的终端-任务关联信息
	List<ComputerTask> getEndedByTask(InspectionTask task);
	//任务已接收
	void SetTaskRecieved(ComputerTask t);
	//检查结果已回传
	void SetTaskReback(String filename);
	//分析检查结果
	void SetTaskAnalysed(ComputerTask t);
	void SetTaskAnalysed(Long computerId,Long taskId);
	void SetTaskStatus(ComputerTask t,String status);
	//获取检查结果
	List<InspectionData> getInspectionDatas(Long computerId,Long taskId,String itemName,boolean bgather);
	//获取指定的检查结果
	List<InspectionData> getInspectionDatas(Long taskId,String itemName,boolean bCompliance,String mark,boolean bgather);
	List<InspectionData> getInspectionDatasById(Long computerTaskId,String itemName,boolean bCompliance,String mark,boolean bgather);
	//从存在数据中获取统计信息
	List<Long> getStat(Long taskId,String itemName,boolean bCompliance,String mark);
	//从不存在的数据中获取统计信息
	List<Long> getStatByNotExist(Long taskId,String itemName,boolean bCompliance,String mark);
	//获取指定任务对应的终端-任务id
	List<Long> getEndedRIDByTask(Long taskId);
	//获取任务下合规的数据
	List<ComputerTask> getCompliancedByTask(Long taskId,boolean bcompliance);
	//获取已回传结果的数据
	List<ComputerTask> getAllRecieved();
}
