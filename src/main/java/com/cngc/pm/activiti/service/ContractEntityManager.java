package com.cngc.pm.activiti.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cngc.pm.activiti.jpa.entity.ContractJpaEntity;

@Service
public class ContractEntityManager {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public ContractJpaEntity getContract(DelegateExecution execution){
		ContractJpaEntity contract = entityManager.find(ContractJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		contract.setProcessInstanceId(execution.getProcessInstanceId());
		contract.setLocked(true);
		contract.setStatus("01");
		entityManager.persist(contract);
		return contract;
	}
	
	@Transactional
	public boolean setContractStauts(DelegateExecution execution){
		ContractJpaEntity contract = (ContractJpaEntity)execution.getVariable("contract");
		String type = execution.getVariable("type").toString();
		if(StringUtils.equals(type, "PUBLISH"))
		{
			contract.setStatus("02");
			contract.setLocked(true);
			entityManager.persist(contract);
		}
		if(StringUtils.equals(type, "MODIFY"))
		{
			contract.setStatus("00");
			contract.setLocked(false);
			entityManager.persist(contract);
		}
		if(StringUtils.equals(type, "DELETE"))
		{
			//knowledge.setStatus(false);
			//knowledge.setLocked(false);
			entityManager.remove(contract);
		}
		
		return true;
	} 
}
