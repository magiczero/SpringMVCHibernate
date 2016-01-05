package com.cngc.pm.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ChangeDAO;
import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.dao.cms.CiDAO;
import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.Change;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.ChangeService;
import com.cngc.utils.Common;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class ChangeServiceImpl implements ChangeService{
	
	@Autowired
	private ChangeDAO changeDao;
	@Autowired
	private ChangeItemDAO changeitemDao;
	@Autowired
	private CiDAO ciDao;
	@Autowired
	private PropertyDAO propertyDao;
	

	@Override
	@Transactional
	public void save(Change event)
	{
		changeDao.save(event);
	}

	@Override
	@Transactional
	public boolean delById(Long id)
	{
		changeDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Change getById(Long id){
		return changeDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Change> getAll()
	{
		return changeDao.findAll();
	}
	@Override
	@Transactional
	public SearchResult<Change> getByIds(List<Long> ids)
	{
		return changeDao.getByIds(ids);
	}
	@Override
	@Transactional
	public Map<String,Object> getCountByStatus()
	{
		return changeDao.getCountByStatus();
	}
	@Override
	@Transactional
	public SearchResult<Change> getByStatus(String status)
	{
		return changeDao.getByStatus(status);
	}
	@Override
	@Transactional
	public SearchResult<Change> getByProcessInstance(List<String> processInstanceIds)
	{
		return changeDao.getByProcessInstance(processInstanceIds);
	}
	@Override
	@Transactional
	public SearchResult<Change> getNotFinished()
	{
		return changeDao.getNotFinished();
	}
	@Override
	@Transactional
	public Map<String,Object> getCountByCategory(String startTime,String endTime)
	{
		return changeDao.getCountByCategory(startTime,endTime);
	}
	@Override
	@Transactional
	public SearchResult<Change> search(String description,String applyUser,String engineer,String risk,Date startDate,Date endDate)
	{
		return changeDao.search(description, applyUser, engineer, risk, startDate, endDate);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status)
	{
		return changeDao.getStats(column, row, startTime, endTime, status);
	}
	@Override
	@Transactional
	public boolean updateCi(Long changeId)
	{
		// 获取item
		Change change = changeDao.find(changeId);
		Set<ChangeItem> items = change.getItems();
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
		return true;
	}
}
