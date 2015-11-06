package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.TaskJpaEntity;

@Service
public class TaskEntityManager {
	
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TaskJpaEntity newTask(DelegateExecution execution) {
    	TaskJpaEntity task = new TaskJpaEntity();
    	
    	task.setProcessInstanceId(execution.getProcessInstanceId());
    	//task.setUserId(execution.getVariable("applyUserId").toString());
    	task.setFromUser(execution.getVariable("fromUser").toString());
    	task.setToUser(execution.getVariable("toUser").toString());
    	task.setTaskTitle(execution.getVariable("taskTitle").toString());
    	task.setTaskDesc(execution.getVariable("taskDesc").toString());
        task.setApplyTime(new Date());
        entityManager.persist(task);
        return task;
    }

    @Transactional
    public void save(TaskJpaEntity task) {
        entityManager.persist(task);
    }

    public TaskJpaEntity getTask(Long id) {
        return entityManager.find(TaskJpaEntity.class, id);
    }
}
