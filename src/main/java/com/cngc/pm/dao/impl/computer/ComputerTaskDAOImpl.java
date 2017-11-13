package com.cngc.pm.dao.impl.computer;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.computer.ComputerTaskDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionTask;
import com.googlecode.genericdao.search.Search;

@Repository
public class ComputerTaskDAOImpl  extends BaseDAOImpl<ComputerTask,Long> implements ComputerTaskDAO{

	@Override
	public List<ComputerTask> getNewTaskByComputer(Computer computer) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("computer", computer);
		search.addFilterEqual("status", "01");
		return this.search(search);
	}

	@Override
	public List<ComputerTask> getByTask(InspectionTask task) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("task", task);
		return this.search(search);
	}

	@Override
	public List<ComputerTask> getByTask(InspectionTask task, String status) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("task", task);
		if(status!="")
			search.addFilterEqual("status", status);
		return this.search(search);
	}

	@Override
	public ComputerTask getByComputerAndTask(Computer computer, InspectionTask task) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("task", task);
		search.addFilterEqual("computer", computer);
		List<ComputerTask> list = this.search(search);
		if(list.size() > 0) {
			 ComputerTask t = list.get(0);
			 return t;
		}
		return null;
	}

	@Override
	public List<ComputerTask> getByIds(List<Long> ids) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterIn("id",ids);
		return this.search(search);
	}

	@Override
	public List<Long> getEndedTaskComputerIdsByTask(Long taskId) {
		// TODO 自动生成的方法存根
		String sql = "select id from cmp_task_computer where task_id=" + taskId + " and status_='04'";

		Query query = this.getSession().createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<Long> list = query.list();
		
		return list;
	}

	@Override
	public List<ComputerTask> getCompliancedByTask(InspectionTask task,
			boolean bcompliance) {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("task",task);
		search.addFilterEqual("status", "04");
		search.addFilterEqual("compliance", bcompliance);
		return this.search(search);
	}

	@Override
	public List<ComputerTask> getAllRecieved() {
		// TODO 自动生成的方法存根
		Search search = new Search(ComputerTask.class);
		search.addFilterEqual("status", "03");
		return this.search(search);
	}

}
