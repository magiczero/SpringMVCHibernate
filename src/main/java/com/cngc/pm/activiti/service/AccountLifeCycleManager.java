package com.cngc.pm.activiti.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.dao.MaintainRecordDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.utils.Common;
import com.googlecode.genericdao.search.Search;

@Service
public class AccountLifeCycleManager {

	@Autowired
	private CiDAO ciDao;
	@Autowired
	private ChangeItemDAO changeItemDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private MaintainRecordDAO maintainRecordDao;
	
	@Transactional
	public boolean afterApprove(DelegateExecution execution){
		Search search = new Search(ChangeItem.class);
		search.addFilterEqual("changeId", Long.valueOf(execution.getProcessInstanceId()));
		List<ChangeItem> items = changeItemDao.search(search);
		
		if(items.size()>0) {
			ChangeItem item = items.get(0);
				switch(item.getType()) {
				case stop:
					item.setNewValue("{\"CMS_FIELD_STATUS\":\"停用\"}");
					changeItemDao.save(item);
					break;
				case scrap:
					item.setNewValue("{\"CMS_FIELD_STATUS\":\"报废\"}");
					changeItemDao.save(item);
					break;
				case destroy:
					item.setNewValue("{\"CMS_FIELD_STATUS\":\"销毁\"}");
					changeItemDao.save(item);
					break;
				case maintain:
					item.setNewValue("{\"CMS_FIELD_STATUS\":\"维修\"}");
					changeItemDao.save(item);
					break;
				default:			//变更和重装操作系统
					break;
				}
				
				
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean setChangeResult(DelegateExecution execution) throws Exception{
		
			Search search = new Search(ChangeItem.class);
			search.addFilterEqual("changeId", Long.valueOf(execution.getProcessInstanceId()));
			List<ChangeItem> items = changeItemDao.search(search);
			
			if(items.size()>0) {
			
				ObjectMapper mapper = new ObjectMapper();
				List<Property> propertylist = propertyDao.getFields();
				
				
//				for(ChangeItem item:items)
//				{
				ChangeItem item = items.get(0);
				Ci ci = ciDao.find(item.getCiId());
				if(item.getType() == ChangeitemType.change || item.getType() == ChangeitemType.reloados) {
					Map<String,Property> fieldmap = new HashMap<String,Property>();
					for(Property p:propertylist)
						fieldmap.put(p.getPropertyId(), p);
					try {
						Map<String,String> newvalues = mapper.readValue(item.getNewValue(), Map.class);
						Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
						
						String ps[] = item.getProperties().split(",");
						for(String s:ps)
						{
							if(s.indexOf("CMS_FIELD_")==0)
							{
								// 更新字段
								Common.setFieldValueByName(ci, fieldmap.get(s).getPropertyConstraint(), newvalues.get(s));
							}
							else
							{
								// 更新参数
								propertymap.put(s, newvalues.get(s));
							}
						}
						ci.setPropertiesData( mapper.writeValueAsString(propertymap) );
						
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(item.getType() == ChangeitemType.stop) {
					ci.setStatus("20");
				} else if(item.getType() == ChangeitemType.scrap) {
					ci.setStatus("11");
				} else if(item.getType() == ChangeitemType.destroy) {
					ci.setStatus("30");
				} else if(item.getType() == ChangeitemType.maintain) {
					ci.setStatus("10");
				}
				
				ciDao.save(ci);
				//同时写入工作记录
				MaintainRecord mr = new MaintainRecord();
				mr.setEquipId(ci.getId().toString());
				mr.setEquipName(ci.getName());
				mr.setEquipNum(ci.getNum());
				mr.setExecutor((String)execution.getVariable("delegateUser"));
				mr.setMaintainTime((Date)execution.getVariable("endTime"));
				mr.setInTime(new Date());
				mr.setType(1);
				mr.setRole(1);
				//设置执行内容
				String oldStr = item.getOldValue();
				String newStr = item.getNewValue();
				for(String s : item.getProperties().split(",")) {
					String propertyName = propertyDao.getPropertyNameByPropertyId(s);
					oldStr = oldStr.replace(s, propertyName);
					newStr = newStr.replace(s, propertyName);
				}
				mr.setCircs("变更前内容为："+oldStr+"，变更后内容为："+newStr);//执行情况
				mr.setEscort((String)execution.getVariable("accompany"));		//陪同人
				mr.setAddIn((String)execution.getVariable("externalEquip")); 		//外接设备情况
				
				maintainRecordDao.save(mr);
				
				item.setUpdatedTime((Date)execution.getVariable("endTime"));
				
				changeItemDao.save(item);
					
//				}
		}
		
		return true;
	} 
}
