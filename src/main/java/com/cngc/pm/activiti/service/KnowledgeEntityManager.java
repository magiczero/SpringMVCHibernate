package com.cngc.pm.activiti.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.KnowledgeJpaEntity;
import com.cngc.utils.PropertyFileUtil;

@Service
public class KnowledgeEntityManager {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public KnowledgeJpaEntity getKnowledge(DelegateExecution execution){
		KnowledgeJpaEntity knowledge = entityManager.find(KnowledgeJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		knowledge.setProcessInstanceId(execution.getProcessInstanceId());
		knowledge.setLocked(true);
		knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.checking") );
		entityManager.persist(knowledge);
		return knowledge;
	}
	
	@Transactional
	public boolean setKnowledgeStauts(DelegateExecution execution){
		KnowledgeJpaEntity knowledge = (KnowledgeJpaEntity)execution.getVariable("knowledge");
		String type = execution.getVariable("type").toString();
		if(StringUtils.equals(type, "PUBLISH"))
		{
			knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.published") );
			entityManager.persist(knowledge);
		}
		if(StringUtils.equals(type, "MODIFY"))
		{
			knowledge.setLocked(false);
			entityManager.persist(knowledge);
		}
		if(StringUtils.equals(type, "DELETE"))
		{
			knowledge.setStatus( PropertyFileUtil.getStringValue("syscode.knowledge.status.deleted") );
			entityManager.persist(knowledge);
		}
		return true;
	} 

}
