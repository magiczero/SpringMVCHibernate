package com.cngc.pm.dao.computer;

import java.util.List;

import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionTask;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface ComputerTaskDAO extends GenericDAO<ComputerTask,Long>{

	//获取终端未接收的检查任务
	List<ComputerTask> getNewTaskByComputer(Computer computer);
	//获取检查任务对应的终端-任务关联记录
	List<ComputerTask> getByTask(InspectionTask task);
	//获取检查任务对应的指定状态的终端-任务关联记录
	List<ComputerTask> getByTask(InspectionTask task,String status);
	//获取终端-任务关联记录
	ComputerTask getByComputerAndTask(Computer computer,InspectionTask task);
	List<Long> getEndedTaskComputerIdsByTask(Long taskId);
	List<ComputerTask> getByIds(List<Long> ids);
	//获取任务下合规的数据
	List<ComputerTask> getCompliancedByTask(InspectionTask task,boolean bcompliance);
	//获取已回传结果的记录
	List<ComputerTask> getAllRecieved();
}
