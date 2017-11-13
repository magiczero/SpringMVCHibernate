package com.cngc.pm.service.impl.cms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.PropertyDAO;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.PropertyService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDAO propertyDao;

	@Override
	@Transactional
	public void save(Property contract) {
		propertyDao.save(contract);
	}

	@Override
	@Transactional
	public boolean delById(Long id) {
		propertyDao.removeById(id);
		return true;
	}

	@Override
	@Transactional
	public Property getById(Long id) {
		return propertyDao.find(id);
	}

	@Override
	@Transactional
	public List<Property> getAll() {
		return propertyDao.findAll();
	}

	@Override
	@Transactional
	public Set<Property> getPropertyByIds(String ids) {
		return propertyDao.getSet(ids);
	}

	@Override
	@Transactional
	public List<Property> getPropertyByNIds(List<Long> ids) {
		return propertyDao.getSetByNIds(ids);
	}
	@Override
	@Transactional
	public List<Property> getFields()
	{
		return propertyDao.getFields();
	}
	@Override
	@Transactional
	public SearchResult<Property> getByPropertyIds(String propertyIds)
	{
		return propertyDao.getByPropertyIds(propertyIds);
	}

	@Override
	public String analyzePropertyToHtml(Property property) {
		// TODO Auto-generated method stub
		String htmlCode = "";
		String validateCode = "";
		if(property.isRequiredField()) {
			validateCode = "validate[required";
		} else {
			validateCode = "validate[optional";
		}
		
		if(property.getMaxLength() != null) {
			validateCode += ",maxSize["+property.getMaxLength()+"]";
		}
		if(property.getMinLength() != null) {
			validateCode += ",minSize["+property.getMinLength()+"]";
		}
		
		if(property.getMaxValue() != null || property.getMinValue() != null) {
			validateCode += ",custom["+property.getPropertyType();
			if(property.getMaxValue() != null && property.getMinValue() != null) {
				validateCode += ",min["+property.getMinValue()+"],max["+property.getMaxValue()+"]";
			} else {
				if(property.getMaxValue() != null) {
					validateCode += ",max["+property.getMaxValue()+"]";
				}
				if(property.getMinValue() != null) {
					validateCode += ",min["+property.getMinValue()+"]";
				}
			}
			
		}
		validateCode += "]";
		if(property.getPropertyType().equals("date")) {
			validateCode += " dateISO";
		}
		
		switch(property.getPropertyType()) {
		case "enum" :
			htmlCode += "<select id=\""+property.getPropertyId()+"\" name=\""+property.getPropertyId()+"\" class=\"" + validateCode + "\" >";
			if(property.getDefaultValue() != null) {
				String options[] = property.getDefaultValue().split(",");
				for(String option : options) {
					htmlCode += "<option value=\""+option+"\">"+option+"</option>";
				}
			}
			htmlCode += "</select>";
			break;
		default:
			String defaultValue = property.getDefaultValue()==null?"":property.getDefaultValue();
			htmlCode += "<input id=\""+property.getPropertyId()+"\" name=\""+property.getPropertyId()+"\" type=\"text\" class=\"" + validateCode + "\" value=\""+defaultValue+"\" />";
			break;
		}
		
		return htmlCode;
	}

	@Override
	@Transactional(readOnly=true)
	public String getPropertyNameByPropertyId(String propertyId) {
		// TODO Auto-generated method stub
		
		return propertyDao.getPropertyNameByPropertyId(propertyId);
	}
}
