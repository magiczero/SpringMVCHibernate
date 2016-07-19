package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.TaskDAO;
import com.cngc.pm.model.Task;

@Service
public class TaskEntityManager {
	
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Resource
//    private MessageService messageService;
	@Autowired
	private TaskDAO taskDao;

    @Transactional
    public Task newTask(DelegateExecution execution) {
    	Task task = new Task();
    	task.setProcessInstanceId(execution.getProcessInstanceId());
    	task.setFromUser(execution.getVariable("fromUser").toString());
    	task.setToUser(execution.getVariable("toUser").toString());
    	task.setTaskTitle(execution.getVariable("taskTitle").toString());
    	task.setTaskContent(execution.getVariable("taskContent").toString());
        task.setApplyTime(new Date());
        task.setDueTime((Date)execution.getVariable("dueTime"));
/*        try
        {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date = sdf.parse(execution.getVariable("dueTime").toString());
        	task.setDueTime(execution.getVariable("dueTime"));
        }
        catch(ParseException e)
        {
        	System.out.println(e.getMessage());
        }*/
//        entityManager.persist(task);
        taskDao.save(task);
        return task;
    }
    @Transactional
    public boolean setTaskStatus(DelegateExecution execution){
    	Task task = (Task)execution.getVariable("mytask");
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				task.setEndbyuser(true);
		}
    	task.setExecutionTime(new Date());
//    	entityManager.persist(task);
    	taskDao.save(task);
    	return true;
    }
    @Transactional
    public void save(Task task) {
//        entityManager.persist(task);
    	taskDao.save(task);
    }

    public Task getTask(Long id) {
//        return entityManager.find(Task.class, id);
    	return taskDao.find(id);
    }
}
