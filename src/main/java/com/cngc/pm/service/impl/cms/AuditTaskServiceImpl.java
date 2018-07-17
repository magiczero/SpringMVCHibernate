package com.cngc.pm.service.impl.cms;

import static com.cngc.utils.Constants._accountMaster;
import static com.cngc.utils.Constants._accountSub;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.exception.BusinessException;
import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.cms.AccountDAO;
import com.cngc.pm.dao.cms.AuditTaskDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.model.cms.TaskCategoryDepartmentRelation;
import com.cngc.pm.service.cms.AuditTaskService;
import com.cngc.utils.Common;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class AuditTaskServiceImpl implements AuditTaskService {

	@Autowired
	private AuditTaskDAO auditTaskDao;
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private ChangeItemDAO changeItemDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private AccountDAO accountDao;
	@Autowired
	private PropertyDAO propertyDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public AuditTask startTask(AuditTask auditTask) {
		// TODO Auto-generated method stub
		//记录开启审核任务，保存工作记录，并且修改CI的状态
		//保存审核任务
		auditTaskDao.save(auditTask);
		//批量修改ci的状态
		for(TaskCategoryDepartmentRelation relation : auditTask.getRelationSet()) {
			Search search = new Search(Ci.class);
			search.addFilterEqual("departmentInUse", String.valueOf(relation.getCiDepartment().getId()));
			search.addFilterEqual("categoryCode", relation.getCiCategory().getCategoryCode());
			//20180711 删除状态正式启用
			search.addFilterEqual("deleteStatus", "01");			//未删除的台账
			
			for(Object obj : ciDao.search(search)) {
				Ci ci = (Ci)obj;//改审核状态为未审核
				ci.setReviewStatus("02");
				
				//在审核信息表中添加一条记录
				ChangeItem item  = new ChangeItem();
				item.setCiId(ci.getId());
				item.setChangeId(auditTask.getId());
				
				item.setCreator(auditTask.getAssessor().getUsername());
				item.setCreatedTime(new java.util.Date());
				item.setType(ChangeitemType.audit);
				item.setPass(false);			//未确认状态
				
				changeItemDao.save(item);
			}
		}
		//同时写入审计记录
		Records record = new Records();
		record.setUsername(auditTask.getAssessor().getName());
		record.setType(RecordsType.user);
		record.setIpAddress("1.0.0.0.0.0");
		record.setDesc("启动了审核任务，任务id：[" + auditTask.getId() +"]");
		
		return auditTask;
	}

	@Override
	public AuditTask findUnfinishedTask(SysUser currentUser) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("assessor", currentUser);
		search.addFilterEmpty("endTime");
		
		return auditTaskDao.searchUnique(search);
	}

	@Override
	public AuditTask findUnfinishedTask(Group group) {
		// TODO Auto-generated method stub
		Search search = new Search(AuditTask.class);
		//search.addFilterSome("relationSet", Filter.equal("ciDepartment", group));
		search.addFilterEmpty("endTime");
		
		for(Object task : auditTaskDao.search(search)) {
			AuditTask temp = (AuditTask)task;
			for(TaskCategoryDepartmentRelation relation : temp.getRelationSet()) {
				if(relation.getCiDepartment() == group) {
					return temp;
				}
			}
		}
		
		return null;
	}

	@Override
	public AuditTask findUnfinishedTask(Group group, Category category) {
		// TODO Auto-generated method stub
		return auditTaskDao.getByGroupAndCode(group, category);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void endTask(SysUser auditMasterUser) throws Exception {
		// TODO Auto-generated method stub
		AuditTask at = this.findUnfinishedTask(auditMasterUser);
		
		if(at==null) throw new Exception("当前没有审核任务");
		
		for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
			//找到所有需要审核的CI
			Search search1 = new Search(Ci.class);
			
			search1.addFilterEqual("departmentInUse", relation.getCiDepartment().getId());		//部门
			search1.addFilterEqual("categoryCode", relation.getCiCategory().getCategoryCode());
			
			for(Object obj : ciDao.search(search1)) {
				Ci ci = (Ci)obj;
				
				if(ci.getReviewStatus().equals("05")){		//审核中的需要把中间表干掉
					Search search = new Search(ChangeItem.class);
					search.addFilterEqual("changeId", at.getId());
					search.addFilterEqual("ciId", ci.getId());
					search.addFilterEqual("type", ChangeitemType.audit);
					
					ChangeItem changeItem = changeItemDao.searchUnique(search);
					
					if(changeItem.getOldValue() == null) {		//新建的
						ciDao.remove(ci);
					} else {
						ci.setReviewStatus("01");
						ciDao.save(ci);
					}
					
					changeItemDao.remove(changeItem);
				} else {
					ci.setReviewStatus("01");
					ciDao.save(ci);
				}
					
			}
			
			//关闭审核任务
			at.setEndTime(new java.sql.Date((new java.util.Date()).getTime()));
			
			auditTaskDao.save(at);
		}
	}

	@Override
	public List<Map<String, Object>> getListByUser(SysUser user,int iDisplayStart,int iDisplayLength) throws Exception {
		// TODO Auto-generated method stub
		List<AuditTask> list = new ArrayList<>();
		if(userDao.isRole(user, roleDao.getByName(_accountMaster))){
			list = auditTaskDao.getListByAssessor(user,iDisplayStart,iDisplayLength);
		} else if(userDao.isRole(user, roleDao.getByName(_accountSub))) {
			list = auditTaskDao.getListByGroup(user.getGroup(),iDisplayStart,iDisplayLength);
		} else 
			throw new Exception("没有权限");
		
		List<Map<String, Object>> list0 = new ArrayList<>();
		
		for(AuditTask at : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", at.getId());
			map.put("name", at.getName());
			map.put("reason", at.getReason());
			map.put("startTime", at.getStartTime());
			map.put("endTime", at.getEndTime());
			Map<String, String> map0 = new HashMap<>();
			SysUser assessor = at.getAssessor();
			map0.put("username", assessor.getUsername());
			map0.put("realname", assessor.getName());
			map.put("assessor", map0);
			Set<Map<String, Object>> setGroup = new HashSet<>();
			Set<Map<String, Object>> setCategory = new HashSet<>();
			Set<Map<String, Object>> setGroupStatus = new HashSet<>();
			boolean status = false;		//
			for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				Map<String, Object> mapGroup = new HashMap<>();
				Map<String, Object> mapCategory = new HashMap<>();
				
				Group group = relation.getCiDepartment();
				mapGroup.put("id", group.getId());
				mapGroup.put("groupName", group.getGroupName());
				setGroup.add(mapGroup);
				
				Category category = relation.getCiCategory();
				mapCategory.put("code", category.getCategoryCode());
				mapCategory.put("codeName", category.getCategoryName());
				setCategory.add(mapCategory);
				
				//以部门为准，忽略其他
				Map<String, Object> mapGroupStatus = new HashMap<>();
				mapGroupStatus.put("id", group.getId());
				int status_ = relation.getStatus();		//状态
				if(status_ == 1) status = true;
				//if(status_ == 2) status = false;
				mapGroupStatus.put("status", status_);
				setGroupStatus.add(mapGroupStatus);
			}
			
			map.put("groups", setGroup);
			map.put("categorys", setCategory);
			map.put("groupStatus", setGroupStatus);
			
			map.put("auditStatus", "");
			if(at.getEndTime() == null) map.put("auditStatus", status);
			
			list0.add(map);
		}
		
		return list0;
	}

	@Override
	public AuditTask isUnTaskWithUser(Long id, SysUser user) throws Exception {
		// TODO Auto-generated method stub
		AuditTask at = auditTaskDao.find(id);
		
		if(at==null)
			throw new Exception("没有这个任务，参数错误 ： "+id);
		
		if(at.getEndTime() == null)
			return null;
		
		if(userDao.isRole(user, roleDao.getByName(_accountMaster))){
			if(at.getAssessor() == user) {
				return at;
			} 
		} else if(userDao.isRole(user, roleDao.getByName(_accountSub))) {
			for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				if(relation.getCiDepartment() == user.getGroup())
					return at;
			}
		} else 
			throw new Exception("没有权限");
		
		return null;
	}

	@Override
	public AuditTask getById(Long id) {
		// TODO Auto-generated method stub
		return auditTaskDao.find(id);
	}
	
	@Override
	public SearchResult<Ci> getCiList(Collection<String> groupIdSet, Collection<String> codeSet, int iDisplayStart,
			int iDisplayLength) {
		// TODO Auto-generated method stub
		Search search = new Search(Ci.class);
		
		search.addFilterIn("departmentInUse", groupIdSet);
		search.addFilterIn("categoryCode", codeSet);
		
		search.addSortAsc("lastUpdateTime");
		
		search.setFirstResult(iDisplayStart);
		search.setMaxResults(iDisplayLength);
		
		return ciDao.searchAndCount(search);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED, readOnly=false)
	public void subAccountCommit(AuditTask at, Group group) throws BusinessException {
		// TODO Auto-generated method stub
		//需要改状态的地方
		Set<TaskCategoryDepartmentRelation> set = new HashSet<>();
		for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
			if(relation.getCiDepartment() == group) {
				relation.setStatus(1);		//部门提交
				//找到所有的ci
				List<Ci> list = ciDao.getListByCodeAndGroup(relation.getCiCategory(), relation.getCiDepartment(), "02");	//未审核的
				
				for(Ci ci : list) {
					//同时看看有没有确认
					ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
					if(item==null || !item.isPass()) {
						throw new BusinessException("有未确认的台账，所以无法提交");
					}
					ci.setReviewStatus("05");
				}
			}
			
			set.add(relation);
		}
		
		at.setRelationSet(set);
		
		auditTaskDao.update(at);
		
	}

	@Override
	public List<Map<String, Object>> getCiMapList(AuditTask at, List<Ci> result) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Integer> map0 = new HashMap<>();
		for(TaskCategoryDepartmentRelation relation : at.getRelationSet()){
			map0.put(relation.getCiDepartment().getId()+relation.getCiCategory().getCategoryCode(), relation.getStatus());
		}

		for(Ci ci : result) {
			Map<String, Object> map = new HashMap<>();
			
			//查看有没有修改
			ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
			
			if(item==null) {
				map.put("action", "");
				map.put("verity", false);
			} else {
				map.put("verity", item.isPass());
				if(item.getActionType()==null)map.put("action", "");
				else
				switch(item.getActionType()) {
					case insert:
						map.put("action", "1");
						break;
					case update:
						map.put("action", "2");
						break;
					default:
						map.put("action", "3");
						break;
				}
				//读取所有的属性
			}
			//部门是否通过
			map.put("departmentcommitstatus", map0.get(ci.getDepartmentInUse()+ci.getCategoryCode()));
			
			List<Property> fieldsSet = getParpertiesByCode(ci.getCategoryCode());
			//赋值
			String action = (String)map.get("action");
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String,String> oldPropertiesmap = mapper.readValue(ci.getPropertiesData(), Map.class);
			if(action.equals("") || action.equals("1") || action.equals("3")) {		//直接读Ci的值
				for(Property p:fieldsSet) {
					String propertyId = p.getPropertyId();
					if(propertyId.indexOf("CMS_FIELD_")==0) {
						map.put(p.getPropertyConstraint(), Common.getFieldValueByName(ci, p.getPropertyConstraint()));
					} else {
						map.put(propertyId, oldPropertiesmap.get(propertyId));
					}
					
				}
			} else {				//否则判断有没有新值
				@SuppressWarnings("unchecked")
				Map<String,String> newPropertymap = mapper.readValue(item.getNewValue(), Map.class);
				String propertiesStr = item.getProperties();
				for(Property p:fieldsSet) {
					Object obj = null;
					String propertyId = p.getPropertyId();
					if(p.getPropertyId().indexOf("CMS_FIELD_")==0) {
						if(propertiesStr.contains(propertyId))
							obj = newPropertymap.get(propertyId);										//读修改后的值
						else
							obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
						map.put(p.getPropertyConstraint(), obj);
					} else {
						if(propertiesStr.contains(propertyId))
							obj = newPropertymap.get(propertyId);										//读修改后的值
						else
							obj = oldPropertiesmap.get(propertyId);
						map.put(propertyId, obj);
					}
					
				}
			}
			
			map.put("id", String.valueOf(ci.getId()));
			
			list.add(map);
			
		}
		return list;
	}

	@Override
	public AuditTask getByGroupAndCategory(Group group, Category category) {
		// TODO Auto-generated method stub
		return auditTaskDao.getByGroupAndCode(group, category);
	}
	
	//根据类型获得要显示的列
	private List<Property> getParpertiesByCode(String code) {
			Search search = new Search(Account.class);
			search.addFilterEqual("category", code);
			
			Account account = accountDao.searchUnique(search);
			
			String fields="", properties = "";
			//获取属性
			switch(account.getType()) {
				case infoSys:
					fields = PropertyFileUtil.getStringValue("account.type.infosys.filed");
					properties = PropertyFileUtil.getStringValue("account.type.infosys.property");
					break;
				case infoEquipment:
					fields = PropertyFileUtil.getStringValue("account.type.infoequipment.filed");
					properties = PropertyFileUtil.getStringValue("account.type.infoequipment.property");
					break;
				case storage:
					fields = PropertyFileUtil.getStringValue("account.type.storage.filed");
					properties = PropertyFileUtil.getStringValue("account.type.storage.property");
					break;
				case security:
					fields = PropertyFileUtil.getStringValue("account.type.security.filed");
					properties = PropertyFileUtil.getStringValue("account.type.security.property");
					break;
				case appSys:
					fields = PropertyFileUtil.getStringValue("account.type.appsys.filed");
					properties = PropertyFileUtil.getStringValue("account.type.appsys.property");
					break;
			}
			
			return propertyDao.getByPropertyIds(fields+","+properties+","+account.getProperties()).getResult();
	}

	@Override
	@Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRED, readOnly=false)
	public void endTask(Long id) throws BusinessException, JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		AuditTask at = auditTaskDao.find(id);
		if(at==null || at.getEndTime()!=null) {
			throw new BusinessException("没有这个审核任务，或者这个审核任务已经结束");
		} else {
			for(TaskCategoryDepartmentRelation relation :at.getRelationSet()) {
				//条件下的所有的ci
				for(Ci ci : ciDao.getListByCodeAndGroup(relation.getCiCategory(), relation.getCiDepartment(), "00")) {
					if(ci.getReviewStatus().equals("04")) {
						ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
						
						if(item==null) {
							throw new BusinessException("台账未做任务操作，也未确认！台账："+ci.getName());
						} else if(item.isPass()) {
							if(item.getActionType()==null) {
								ci.setReviewStatus("01");
							} else {
								switch(item.getActionType()) {
								case insert:
									ci.setReviewStatus("01");
									break;
								case update:
									//修改ci
									ObjectMapper mapper = new ObjectMapper();
									List<Property> propertylist = propertyDao.getFields();
									@SuppressWarnings("unchecked")
									Map<String,String> newPropertymap = mapper.readValue(item.getNewValue(), Map.class);
									@SuppressWarnings("unchecked")
									Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
									//需修改的属性
									String ps[] = item.getProperties().split(",");
									
									Map<String,Property> fieldmap = new HashMap<String,Property>();
									for(Property p:propertylist)
										fieldmap.put(p.getPropertyId(), p);
									for(String s:ps)
									{
										Property p0 = fieldmap.get(s);
										if(s.indexOf("CMS_FIELD_")==0){
											// 更新字段
											try {
												Object objValue;	//只有Date和String两个类型
												if(p0.getPropertyType().equals("date")) {
													SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
													objValue = formatter.parse(newPropertymap.get(s));
												} else if(p0.getPropertyType().equals("sqldate")){
													SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
													java.util.Date date = formatter.parse(newPropertymap.get(s));
													objValue = new java.sql.Date(date.getTime());
												}else {
													objValue = newPropertymap.get(s);
												}
												Common.setFieldValueByName(ci, p0.getPropertyConstraint(), objValue);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												throw new BusinessException("未知错误，无法更新");
											}
										}else{
											// 更新参数
											propertymap.put(s, newPropertymap.get(s));
										}
									}
									ci.setPropertiesData( mapper.writeValueAsString(propertymap) );
									ci.setReviewStatus("01");
									break;
								default:	//删除
									ci.setReviewStatus("01");
									ci.setDeleteStatus("00");		//删除状态
									ci.setDeleteTime(new java.util.Date());
									break;
								}
							}
							
						} else {
							throw new BusinessException("台账未确认！台账："+ci.getName());
						}
						
					}else {
						throw new BusinessException("台账的审核状态不正确，无法完成操作："+ci.getName());
					}
					//relation.setStatus(1);
				}
			}
		}
		at.setEndTime(new java.sql.Date((new java.util.Date()).getTime()));
	}

	@Override
	public List<Ci> getCiListByGroupAndCategory(Category ciCategory, Group ciDepartment) {
		// TODO Auto-generated method stub
		return ciDao.getListByCodeAndGroup(ciCategory, ciDepartment, "00");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void verity(AuditTask at, Ci ci) {
		// TODO Auto-generated method stub
		ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
		if(item == null) {		//新建一个
			item = new ChangeItem();
			item.setCiId(ci.getId());
			item.setChangeId(at.getId());
			
			
			item.setCreatedTime(new java.util.Date());
			item.setType(ChangeitemType.audit);
			item.setPass(true);
			changeItemDao.save(item);
		} else//修改确认列
			item.setPass(true);
		
		ci.setReviewStatus("02");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public void verityByCiids(AuditTask at, Long[] ids) {
		// TODO Auto-generated method stub
		for(Ci ci:ciDao.find(ids)) {
			ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
			if(item == null) {		//新建一个
				item = new ChangeItem();
				item.setCiId(ci.getId());
				item.setChangeId(at.getId());
				
				
				item.setCreatedTime(new java.util.Date());
				item.setType(ChangeitemType.audit);
				item.setPass(true);
				changeItemDao.save(item);
			} else//修改确认列
				item.setPass(true);
			
			ci.setReviewStatus("02");
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED, readOnly = false)
	public void reviewAudit(Long atid, Long groupid) throws BusinessException {
		// TODO Auto-generated method stub
		AuditTask at = auditTaskDao.find(atid);
		
		for(TaskCategoryDepartmentRelation relation :at.getRelationSet()) {
			if(groupid==0l) {
				for(Ci ci : ciDao.getListByCodeAndGroup(relation.getCiCategory(), relation.getCiDepartment(), "00")){
					//有审核状态不正确的
					if(ci.getReviewStatus().equals("05")) {
						throw new BusinessException("此台账审核状态不正确："+ci.getName());
					}
				}
				relation.setStatus(0);
			} else {
				if(relation.getCiDepartment().getId().equals(groupid)) {
					for(Ci ci : ciDao.getListByCodeAndGroup(relation.getCiCategory(), relation.getCiDepartment(), "00")){
						//有审核状态不正确的
						if(ci.getReviewStatus().equals("05")) {
							throw new BusinessException("此台账审核状态不正确："+ci.getName());
						} else if(ci.getReviewStatus().equals("03")) {
							ChangeItem item = changeItemDao.getByCiIdAndAuditTask(ci.getId(), at);
							item.setPass(false);
						}
					}
					relation.setStatus(0);
				}
			}
		}
	}

	@Override
	public SearchResult<Ci> getCiListByAudit(Long auditTaskId, Collection<String> groupIdSet,
			Collection<String> codeSet, int iDisplayStart, int iDisplayLength) {
		// TODO Auto-generated method stub
		return auditTaskDao.getCiList(auditTaskId, groupIdSet, codeSet, iDisplayStart, iDisplayLength);
	}

	@Override
	public int getCountByStatus(long at, Long groupId, String categoryCode, boolean isCommit, String status) {
		// TODO Auto-generated method stub
		return auditTaskDao.getCountByCondition(at, groupId, categoryCode, isCommit, status);
	}

}
