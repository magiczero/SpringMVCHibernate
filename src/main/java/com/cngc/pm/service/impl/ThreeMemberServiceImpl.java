package com.cngc.pm.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.cngc.exception.ResourceNotFoundException;
import com.cngc.pm.dao.GroupDAO;
import com.cngc.pm.dao.ManagerFormDAO;
import com.cngc.pm.dao.RelationshipDAO;
import com.cngc.pm.dao.StyleDAO;
import com.cngc.pm.dao.ThreeMemberRelationDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.WorkRecordDAO;
import com.cngc.pm.exception.NotDeleteAuthorityException;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.model.manage.Relationship;
import com.cngc.pm.model.manage.WorkRecord;
import com.cngc.pm.service.ThreeMemberService;
import com.cngc.pm.threemember.template.Table2;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

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
	private RelationshipDAO relationshipDao;
	@Autowired
	private ManagerFormDAO managerFormDao;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private FormService formService;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private StyleDAO styleDao;
	@Autowired
	private GroupDAO groupDao;
	@Autowired
	private WorkRecordDAO recordDao;
	
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
				dataMap.put("executor", userDao.getUserByUserName((String)hvi.getValue()).getName());
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

	@Override
	public boolean haveRelationshipByCmdbAndRole(Ci cmdb, ManageType mt) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("cmdb", cmdb);
		search.addFilterEqual("role", mt);
		search.addFilterEqual("del", false);
		
		if(relationshipDao.search(search).size()>0)
			return true;
		
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void saveRelationshipWithBatch(SysUser sysUser, ManageType mt, List<Ci> cmdbList) {
		// TODO Auto-generated method stub
		for(Ci ci : cmdbList) {
			Relationship rs = new Relationship();
			rs.setUser(sysUser);
			rs.setRole(mt);
			rs.setCmdb(ci);
			
			relationshipDao.save(rs);
		}
	}

	@Override
	public boolean haveRelationshipByCmdbAndUser(Ci cmdb, SysUser selectedUser) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("cmdb", cmdb);
		search.addFilterEqual("user", selectedUser);
		search.addFilterEqual("del", false);
		
		if(relationshipDao.search(search).size()>0)
			return true;
		
		return false;
	}

	@Override
	public List<Relationship> getAllRelationshipList() {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("del", false);
		search.addSortAsc("cmdb");
		
		return relationshipDao.search(search);
	}

	@Override
	public List<Relationship> getRelationshipListByUnit(Group unit) {
		// TODO Auto-generated method stub
		List<Relationship> list = new ArrayList<>();
		for(Relationship r : getAllRelationshipList()) {
			String groupIdStr = r.getCmdb().getDepartmentInUse();
			Group group = groupDao.find(Long.parseLong(groupIdStr));
			
			Group topGroup = getTopGroup(group);
			
			if(topGroup.getId() == unit.getId())
				list.add(r);
		}
		
		return list;
	}

	@Override
	public List<Relationship> getRelationshipList(Group unit, Ci searchCmdb, ManageType mt, SysUser searchUser) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		if(searchCmdb != null)
			search.addFilterEqual("cmdb", searchCmdb);
		
		if(mt != null)
			search.addFilterEqual("role", mt);
		
		if(searchUser != null)
			search.addFilterEqual("user", searchUser);
		
		search.addFilterEqual("del", false);
		
		List<Relationship> list1 = relationshipDao.search(search);
		
		if(unit == null)
			return list1;
		else {
			List<Relationship> list = new ArrayList<>();
			for(Relationship r : list1) {
				String groupIdStr = r.getCmdb().getDepartmentInUse();
				Group group = groupDao.find(Long.parseLong(groupIdStr));
				
				Group topGroup = getTopGroup(group);
				
				if(topGroup.getId() == unit.getId())
					list.add(r);
			}
			
			return list;
		}
	}
	
	Group getTopGroup(Group group) {
		
		Group groupParent = group.getParentGroup();
		if(groupParent==null)
			return group;
		else
			return getTopGroup(groupParent);
	}

	@Override
	public SearchResult<WorkRecord> getWorkrecord(SysUser searchUser, ManageType mt, Ci searchCmdb, Date startTime_,
			Date endTime_, int offset, int maxResults) {
		// TODO Auto-generated method stub
		Search search = new Search(WorkRecord.class);
		
		if(searchUser != null)
			search.addFilterEqual("auth.user", searchUser);
		
		if(mt != null)
			search.addFilterEqual("auth.role", mt);
		
		if(searchCmdb != null)
			search.addFilterEqual("auth.cmdb", searchCmdb);
		
		if(startTime_ != null)
			search.addFilterGreaterOrEqual("recordTime", startTime_);
		
		if(endTime_ != null)
			search.addFilterLessOrEqual("recordTime", endTime_);
		
		search.addSortDesc("recordTime");
		
		search.setFirstResult(offset);
		search.setMaxResults(maxResults);
		
		return recordDao.searchAndCount(search);
	}

	@Override
	public List<Relationship> getRelationshipListByUser(SysUser currentUser) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("user", currentUser);
		
		return relationshipDao.search(search);
	}

	@Override
	public ManageType getMtByCmdb(SysUser user, int cmdbId) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("user", user);
		search.addFilterEqual("cmdb.id", cmdbId);
		search.addFilterEqual("del", false);
		
		return ((Relationship)relationshipDao.searchUnique(search)).getRole();
	}

	@Override
	public List<Relations> getRelationListByItemAndMt(int itemId, int mt) {
		// TODO Auto-generated method stub
		Search search = new Search(Relations.class);
		search.addFilterEqual("item.id", itemId);
		search.addFilterEqual("type", ManageType.get(mt));
		
		return relationDao.search(search);
	}

	@Override
	public Relations getRelationOne(ManageType mt, int actionId, int itemId) {
		// TODO Auto-generated method stub
		Search search = new Search(Relations.class);
		search.addFilterEqual("item.id", itemId);
		search.addFilterEqual("type", mt);
		search.addFilterEqual("action.id", actionId);
		
		return relationDao.searchUnique(search);
	}

	@Override
	public Relationship getRelationshipOne(ManageType mt, SysUser user, int cmdbId) {
		// TODO Auto-generated method stub
		Search search = new Search(Relationship.class);
		
		search.addFilterEqual("user", user);
		search.addFilterEqual("role", mt);
		search.addFilterEqual("cmdb.id", cmdbId);
		search.addFilterEqual("del", false);
		
		return relationshipDao.searchUnique(search);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean saveWorkrecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		return recordDao.save(wr);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean deleteWorkrecordById(Long id, SysUser user) {
		// TODO Auto-generated method stub
		//首先，判断权限
		WorkRecord wr = recordDao.find(id);
		if(wr == null) throw new ResourceNotFoundException(id);
		
		if(wr.getAuth().getUser().getId() == user.getId()) {	//有权限删除
			return recordDao.remove(wr);
		} else {
			throw new NotDeleteAuthorityException(id, (WorkRecord.class).getName(), user.getUsername());
		}
	}

}
