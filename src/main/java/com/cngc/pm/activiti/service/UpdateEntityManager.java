package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.UpdateDAO;
import com.cngc.pm.model.Update;

@Service
public class UpdateEntityManager {
//    @PersistenceContext
//    private EntityManager entityManager;
	@Autowired
	private UpdateDAO updateDao;
    
    @Transactional
    public Update newUpdate(DelegateExecution execution) {
    	Update update = new Update();
    	update.setProcessInstanceId(execution.getProcessInstanceId());
    	update.setUserId(execution.getVariable("user").toString());
    	update.setUpdateType(execution.getVariable("updateType").toString());
    	update.setCreatedTime(new Date());
//        entityManager.persist(update);
    	updateDao.save(update);
        return update;
    }
    @Transactional
    public boolean setUpdateStatus(DelegateExecution execution){
    	Update update = (Update)execution.getVariable("update");
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				update.setEndbyuser(true);
		}
    	update.setExecutionTime(new Date());
    	updateDao.save(update);
    	return true;
    }
}
