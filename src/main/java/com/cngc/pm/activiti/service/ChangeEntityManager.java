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

import com.cngc.pm.dao.ChangeDAO;
import com.cngc.pm.dao.MaintainRecordDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.Change;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.utils.Common;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;


@Service
public class ChangeEntityManager {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Resource
//    private ChangeService changeService;
    @Autowired
    private ChangeDAO changeDao;
    @Autowired
	private CiDAO ciDao;
	@Autowired
	private PropertyDAO propertyDao;
	@Autowired
	private MaintainRecordDAO maintainRecordDao;
    
	@Transactional
	public Change getChange(DelegateExecution execution){
		Change change = changeDao.find( Long.valueOf(execution.getVariable("id").toString()) );
		change.setProcessInstanceId(execution.getProcessInstanceId());
		change.setApplyTime(new Date());
		//entityManager.persist(change);
		changeDao.save(change);
		return change;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean setChangeStatus(DelegateExecution execution,String status) throws Exception{
		Change change = (Change)execution.getVariable("change");
		Change newChange = changeDao.find(change.getId());
		newChange.setStatus( status );
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				newChange.setEndbyuser(true);
		}
		if( status.equals(PropertyFileUtil.getStringValue("syscode.change.status.finished")) )
		{
			//changeService.updateCi(change.getId());
			Search search = new Search(Change.class);
			search.addField("items");
			search.addFilterEqual("id", change.getId());
			List<ChangeItem> items = changeDao.search(search);
			// 获取item
//			Change change1 = changeDao.find(change.getId());
//			Set<ChangeItem> items = change1.getItems();
			ObjectMapper mapper = new ObjectMapper();
			List<Property> propertylist = propertyDao.getFields();
			Map<String,Property> fieldmap = new HashMap<String,Property>();
			for(Property p:propertylist)
				fieldmap.put(p.getPropertyId(), p);
			
			for(ChangeItem item:items)
			{
				Ci ci = ciDao.find(item.getCiId());
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
					ciDao.save(ci);
					//同时写入工作记录
					MaintainRecord mr = new MaintainRecord();
					mr.setEquipId(ci.getId().toString());
					mr.setEquipName(ci.getName());
					mr.setEquipNum(ci.getNum());
					mr.setExecutor(newChange.getApplyUser());
					mr.setMaintainTime(newChange.getApplyTime());
					mr.setType(1);
					mr.setRole(1);
					mr.setCircs("变更前内容为："+item.getOldValue()+"，变更后内容为："+item.getNewValue());//执行情况
					
					maintainRecordDao.save(mr);
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
				
			}
			newChange.setEndTime(new Date());
		}
		//entityManager.persist(change);
		
		changeDao.save(newChange);
		changeDao.flush();
		return true;
	} 
}
