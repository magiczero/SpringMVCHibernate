package com.cngc.pm.activiti.service;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.KnowledgeDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.utils.PropertyFileUtil;

@Service
public class KnowledgeEntityManager {
//	@PersistenceContext
//    private EntityManager entityManager;
	@Autowired
	private KnowledgeDAO knowledgeDao;
	
	@Transactional
	public Knowledge getKnowledge(DelegateExecution execution){
		Knowledge knowledge = knowledgeDao.find( Long.valueOf(execution.getVariable("id").toString()) );
		knowledge.setProcessInstanceId(execution.getProcessInstanceId());
		knowledge.setLocked(true);
		knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.checking") );
//		entityManager.persist(knowledge);
		knowledgeDao.save(knowledge);
		return knowledge;
	}
	
	@Transactional
	public boolean setKnowledgeStauts(DelegateExecution execution){
		Knowledge knowledge = (Knowledge)execution.getVariable("knowledge");
		String type = execution.getVariable("type").toString();
		if(StringUtils.equals(type, "PUBLISH"))
		{
			knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.published") );
			knowledgeDao.save(knowledge);
		}
		if(StringUtils.equals(type, "MODIFY"))
		{
			knowledge.setLocked(false);
			knowledgeDao.save(knowledge);
		}
		if(StringUtils.equals(type, "DELETE"))
		{
			knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.deleted") );
			knowledgeDao.save(knowledge);
		}
		return true;
	} 

}
