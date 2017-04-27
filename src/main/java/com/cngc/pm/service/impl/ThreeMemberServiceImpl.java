package com.cngc.pm.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ManagerFormDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.dao.ThreeMemberRelationDAO;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.service.ThreeMemberService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.threemember.template.Table2;
import com.googlecode.genericdao.search.Search;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class ThreeMemberServiceImpl implements ThreeMemberService {

//	@Autowired
//	private ThreeItemDAO itemDao;
//	@Autowired
//	private ThreeActionDAO actionDao;
	@Autowired
	private ThreeMemberRelationDAO relationDao;
	@Autowired
	private ManagerFormDAO managerFormDao;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private FormService formService;
	@Resource
	private UserService userService;
	@Autowired
	private StyleDAO styleDao;
	
	@Override
	public List<Relations> getRelationItemListByType(ManageType type) {
		// TODO Auto-generated method stub
		
		return relationDao.getItemsByType(type);
	}

	@Override
	public List<Relations> getRelationListByType(ManageType type) {
		// TODO Auto-generated method stub
		return relationDao.getListByType(type);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(ManagerForm form, Task task) {
		// TODO Auto-generated method stub
		form.setProcessInstanceId(task.getProcessInstanceId());
		
		managerFormDao.save(form);
		
		taskService.complete(task.getId());
	}

	@Override
	public ManagerForm getFormByPid(String pid) {
		// TODO Auto-generated method stub
		Search search = new Search(ManagerForm.class);
		
		search.addFilterEqual("processInstanceId", pid);
		
		return managerFormDao.searchUnique(search);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDocumentData(ManagerForm mf) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(mf.getValue(), Map.class);
		
		List<String> itemStr = (List<String>)map.get("rid");
		List<Table2>  list=new ArrayList<>();
		//差不多就是一条
		for(String s : itemStr) {
			Relations r = relationDao.find(Long.valueOf(s));
			
			Table2 t2 = new Table2();
			
			t2.setItemName(r.getItem().getName());
			t2.setActionName(r.getAction().getName());
			//详细信息
			List<String> details = (List<String>)map.get(s+"_1");
			t2.setDetail(details.get(0));
			//依据
			List<String> basis = (List<String>)map.get(s+"_2");
			t2.setBasis(basis.get(0));
			
			list.add(t2);
		}
		Map<String, Object> dataMap = new HashMap<>();
		
		dataMap.put("list", list);
		
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(mf.getProcessInstanceId()).singleResult();
		
		List<HistoricVariableInstance> hviList = historyService.createHistoricVariableInstanceQuery().processInstanceId(mf.getProcessInstanceId()).list();
		
		for(HistoricVariableInstance hvi : hviList) {
			if(hvi.getVariableName().equals("threemember")) {
				dataMap.put("executor", userService.getUserName((String)hvi.getValue()));
			} else if(hvi.getVariableName().equals("type")) {
				switch((String)hvi.getValue()) {
		    		case "1" :
		    			dataMap.put("typeName", "系统管理员");
		    			break;
		    		case "2" :
		    			dataMap.put("typeName", "安全管理员");
		    			break;
		    		case "3":
		    			dataMap.put("typeName", "安全审计员");
		    			break;	
		    		case "4" :
		    			dataMap.put("typeName", "保密管理平台系统管理员");
		    			break;
		    		case "5" :
		    			dataMap.put("typeName", "保密管理平台安全管理员");
		    			break;
		    		case "6":
		    			dataMap.put("typeName", "保密管理平台安全审计员");
		    			break;	
		    		case "7" :
		    			dataMap.put("typeName", "oa系统管理员");
		    			break;
		    		case "8" :
		    			dataMap.put("typeName", "oa安全管理员");
		    			break;
		    		case "9":
		    			dataMap.put("typeName", "oa安全审计员");
		    			break;	
				}
	    	}
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		dataMap.put("time", sdf.format(hpi.getStartTime()));
		
		return dataMap;
	}

	@Override
	public Style getSystem() {
		// TODO Auto-generated method stub
		return styleDao.getByCode("TM");
	}

}
