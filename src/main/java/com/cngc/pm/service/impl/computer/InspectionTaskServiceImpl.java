package com.cngc.pm.service.impl.computer;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.ComputerDAO;
import com.cngc.pm.dao.computer.ComputerTaskDAO;
import com.cngc.pm.dao.computer.InspectionDataDAO;
import com.cngc.pm.dao.computer.InspectionTaskDAO;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionData;
import com.cngc.pm.model.computer.InspectionTask;
import com.cngc.pm.service.computer.InspectionTaskService;
import com.cngc.utils.PropertyFileUtil;

@Service
public class InspectionTaskServiceImpl implements InspectionTaskService{

	@Autowired
	private InspectionTaskDAO taskDao;
	@Autowired
	private ComputerTaskDAO computerTaskDao;
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private InspectionDataDAO inspectionDataDao;
	@Autowired
	private FormService formService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(InspectionTask inspectionTask,String userid) throws Exception {
		// TODO 自动生成的方法存根
		taskDao.save(inspectionTask);
		taskDao.flush();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.computerinspection")).active()
				.latestVersion().singleResult();
		if (processDefinition == null) throw new Exception("未找到相应的事件流程");
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("id", String.valueOf(inspectionTask.getId()));
		identityService.setAuthenticatedUserId(userid);
		formService.submitStartFormData(processDefinition.getId(), variables);
	}

	@Override
	@Transactional
	public boolean delById(Long taskId) {
		// TODO 自动生成的方法存根
		taskDao.removeById(taskId);
		return true;
	}

	@Override
	@Transactional
	public InspectionTask getById(Long taskId) {
		// TODO 自动生成的方法存根
		return taskDao.find(taskId);
	}

	@Override
	public List<InspectionTask> getAll() {
		// TODO 自动生成的方法存根
		return taskDao.findAll();
	}

	@Override
	public List<ComputerTask> getNewTaskByComputer(Computer computer) {
		// TODO 自动生成的方法存根
		List<ComputerTask> list = computerTaskDao.getNewTaskByComputer(computer);
		return list;
	}

	@Override
	public List<ComputerTask> getByIds(List<Long> ids) {
		// TODO 自动生成的方法存根
		return computerTaskDao.getByIds(ids);
	}

	@Override
	public List<ComputerTask> getByTask(InspectionTask task) {
		// TODO 自动生成的方法存根
		List<ComputerTask> list = computerTaskDao.getByTask(task);
//		List<Computer> list = new ArrayList<Computer>();
//		for(ComputerTask t:tasks){
//			list.add(t.getComputer());
//		}
		return list;
	}

	@Override
	public List<ComputerTask> getEndedByTask(InspectionTask task) {
		// TODO 自动生成的方法存根
		List<ComputerTask> list = computerTaskDao.getByTask(task, "04");
		return list;
	}

	@Override
	@Transactional
	public void SetTaskRecieved(ComputerTask t) {
		// TODO 自动生成的方法存根
		//SetTaskStatus(t,"02");
		t.setStatus("02");
		computerTaskDao.save(t);
	}

	@Override
	@Transactional
	public void SetTaskAnalysed(ComputerTask t) {
		// TODO 自动生成的方法存根
		//SetTaskStatus(t,"04");
		Computer computer = t.getComputer();
		if(computer!=null)
		{
			//更新终端最后一次检查
			computer.setLastCheckDate(new Timestamp(System.currentTimeMillis()));
			computer.setLastTaskId(t.getTask().getId());
			computerDao.save(computer);
		}
		t.setStatus("04");
		computerTaskDao.save(t);
	}

	@Override
	@Transactional
	public void SetTaskAnalysed(Long computerId, Long taskId) {
		// TODO 自动生成的方法存根
		Computer computer = computerDao.find(computerId);
		InspectionTask task = taskDao.find(taskId);
		if(computer!=null&&task!=null)
		{
			//更新终端最后一次检查
			computer.setLastCheckDate(new Timestamp(System.currentTimeMillis()));
			computer.setLastTaskId(taskId);
			computerDao.save(computer);
			
			ComputerTask t = computerTaskDao.getByComputerAndTask(computer, task);
			if(t!=null)
			{
				//SetTaskAnalysed(t);
				t.setStatus("04");
				computerTaskDao.save(t);
			}
		}
	}

	@Override
	@Transactional
	public void SetTaskReback(String filename) {
		// TODO 自动生成的方法存根
		String[] skeys = filename.split("_");
		if(skeys.length!=3)
			return;
		Computer computer = computerDao.find(Long.parseLong(skeys[0]));
		InspectionTask task = taskDao.find(Long.parseLong(skeys[1]));
		if(computer!=null&&task!=null)
		{
			//更新状态
			ComputerTask t = computerTaskDao.getByComputerAndTask(computer, task);
			if(t!=null)
			{
				t.setStatus("03");
				t.setFile(filename);
				computerTaskDao.save(t);
			}
		}
	}

	@Override
	@Transactional
	public void SetTaskStatus(ComputerTask t, String status) {
		// TODO 自动生成的方法存根
		t.setStatus(status);
		computerTaskDao.save(t);
	}

	@Override
	public List<InspectionData> getInspectionDatas(Long computerId,
			Long taskId, String itemName,boolean bgather) {
		// TODO 自动生成的方法存根
		Computer computer = computerDao.find(computerId);
		InspectionTask task = taskDao.find(taskId);
		List<InspectionData> list = null;
		if(computer!=null&&task!=null)
		{
			ComputerTask t = computerTaskDao.getByComputerAndTask(computer, task);
			if(t!=null)
			{
				list = inspectionDataDao.getData(t.getId(), itemName,bgather);
			}
		}
		return list;
	}

	@Override
	public List<InspectionData> getInspectionDatas(Long taskId, String itemName,boolean bCompliance,String mark,boolean bgather) {
		// TODO 自动生成的方法存根
		List<InspectionData> list = null;
		
		//获取task
		InspectionTask task = taskDao.find(taskId);
		if(task==null)
			return null;
		//获取computers
		List<ComputerTask> lstask = computerTaskDao.getByTask(task);
		if(lstask==null)
			return null;
		
		Set<Long> ids = new HashSet<Long>();
		for(int i=0;i<lstask.size();i++)
			ids.add(lstask.get(i).getId());
		list = inspectionDataDao.getData(ids, itemName,bCompliance,mark,bgather);

		return list;
	}

	@Override
	public List<InspectionData> getInspectionDatasById(Long computerTaskId,
			String itemName, boolean bCompliance, String mark, boolean bgather) {
		// TODO 自动生成的方法存根
		Set<Long> ids = new HashSet<Long>();
		ids.add(computerTaskId);
		return inspectionDataDao.getData(ids, itemName,bCompliance,mark,bgather);
	}

	@Override
	public List<Long> getStat(Long taskId, String itemName,
			boolean bCompliance, String mark) {
		// TODO 自动生成的方法存根
		return inspectionDataDao.getStats(taskId, itemName, bCompliance, mark);
	}
	
	@Override
	public List<Long> getStatByNotExist(Long taskId, String itemName,
			boolean bCompliance, String mark) {
		// TODO 自动生成的方法存根
		return inspectionDataDao.getStatsByNotExist(taskId, itemName, bCompliance, mark);
	}
	
	@Override
	public List<Long> getEndedRIDByTask(Long taskId) {
		// TODO 自动生成的方法存根
		return computerTaskDao.getEndedTaskComputerIdsByTask(taskId);
	}

	@Override
	public List<ComputerTask> getCompliancedByTask(Long taskId,
			boolean bcompliance) {
		// TODO 自动生成的方法存根
		return computerTaskDao.getCompliancedByTask(taskDao.find(taskId), bcompliance);
	}

	@Override
	@Transactional
	public List<ComputerTask> getAllRecieved() {
		// TODO 自动生成的方法存根
		return computerTaskDao.getAllRecieved();
	}

}
