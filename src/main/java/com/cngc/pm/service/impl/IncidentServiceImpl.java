package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.model.Incident;
import com.cngc.pm.service.IncidentService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentDAO incidentDao;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private FormService formService;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(Incident incident, String userid) throws Exception {
		incidentDao.save(incident);
		System.out.println("transaction start ...");
		int i = 1 / 0;
		System.out.println(i);
		// 启动流程
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
							.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
							.latestVersion().singleResult();
		if (processDefinition == null) throw new Exception("未找到相应的事件流程");
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("id", String.valueOf(incident.getId()));
		identityService.setAuthenticatedUserId(userid);
		formService.submitStartFormData(processDefinition.getId(), variables);
					
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean delByIds(String ids) {
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for (int i = 0; i < id.length; i++) {
			String str = id[i];
			if (!isNumeric(str)) {
				return false;
			}
			idss[i] = Long.valueOf(str);
		}
		try {
			for (Long k : idss) {
				incidentDao.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean delById(Long id) {
		incidentDao.removeById(id);
		return true;
	}

	@Override
	public Incident getById(Long id) {
		return incidentDao.find(id);
	}

	@Override
	public List<Incident> getAll() {
		return incidentDao.findAll();
	}

	@Override
	public Map<String, Object> getCountByStatus() {
		return incidentDao.getCountByStatus();
	}

	@Override
	public SearchResult<Incident> getByStatus(String status) {
		return incidentDao.getByStatus(status);
	}

	@Override
	public SearchResult<Incident> search(String abs, String applyUser, String engineer, String satisfaction,
			Date startTime, Date endTime, Integer offset, Integer maxResults) {
		Search search = new Search();
		if (applyUser != null) {
			if (!applyUser.isEmpty())
				search.addFilterEqual("applyUser", applyUser);
		}
		if (engineer != null) {
			if (!engineer.isEmpty())
				search.addFilterEqual("currentDelegateUser", engineer);
		}
		if (satisfaction != null)
			search.addFilterEqual("satisfaction", satisfaction);
		if (startTime != null)
			search.addFilterGreaterOrEqual("applyTime", startTime);
		if (endTime != null)
			search.addFilterLessOrEqual("recoverTime", endTime);
		if (abs != null) {
			if (!abs.isEmpty())
				search.addFilterLike("abs", "%" + abs + "%");
		}

		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		if(maxResults>-1) {
			search.setFirstResult(offset == null ? 0 : offset);
			search.setMaxResults(maxResults == null ? 10 : maxResults);
		}
		search.addSort("applyTime", true);

		return incidentDao.searchAndCount(search);
	}

	@Override
	public SearchResult<Incident> getNotFinished() {
		return incidentDao.getNotFinished();
	}

	@Override
	public SearchResult<Incident> getByProcessInstance(List<String> processInstanceIds) {
		return incidentDao.getByProcessInstance(processInstanceIds);
	}
	
	@Override
	public Long getIdByProcessInstance(String processInstanceId){
		List<String> ids = new ArrayList<String>();
		ids.add(processInstanceId);
		SearchResult<Incident> re = getByProcessInstance(ids);
		if(re.getTotalCount()>0)
			return re.getResult().get(0).getId();
		else
			return Long.getLong("0");
	}
	@Override
	public Map<String, Object> getCountByCategory(String startTime, String endTime) {
		return incidentDao.getCountByCategory(startTime, endTime);
	}

	@Override
	public Map<String, Object> getStats(String column, String row, String startTime, String endTime, String status) {
		return incidentDao.getStats(column, row, startTime, endTime, status);
	}

	@Override
	public SearchResult<Incident> getByApplyUser(String user) {
		return incidentDao.getByApplyUser(user);
	}

	@Override
	public SearchResult<Incident> getByApplyUser(String user, Boolean bClose) {
		return incidentDao.getByApplyUser(user, bClose);
	}

	@Override
	public SearchResult<Incident> getByIds(List<Long> ids) {
		return incidentDao.getByIds(ids);
	}

	@Override
	public List<Incident> search(String abs, String applyUser,
			String engineer, String satisfaction, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		Search search = new Search();
		if (applyUser != null) {
			if (!applyUser.isEmpty())
				search.addFilterEqual("applyUser", applyUser);
		}
		if (engineer != null) {
			if (!engineer.isEmpty())
				search.addFilterEqual("currentDelegateUser", engineer);
		}
		if (satisfaction != null)
			search.addFilterEqual("satisfaction", satisfaction);
		if (startTime != null)
			search.addFilterGreaterOrEqual("applyTime", startTime);
		if (endTime != null)
			search.addFilterLessOrEqual("recoverTime", endTime);
		if (abs != null) {
			if (!abs.isEmpty())
				search.addFilterLike("abs", "%" + abs + "%");
		}

		search.addFilterEqual("status", PropertyFileUtil.getStringValue("syscode.incident.status.finished"));
		search.addSort("applyTime", true);
		return incidentDao.search(search);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void update(Incident newincident) {
		// TODO Auto-generated method stub
		incidentDao.save(newincident);
	}

}
