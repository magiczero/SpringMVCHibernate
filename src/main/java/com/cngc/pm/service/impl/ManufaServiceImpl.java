package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ManufaDAO;
import com.cngc.pm.model.Manufacturer;
import com.cngc.pm.service.ManufaService;
import static com.cngc.utils.Common.isNumeric;;

@Service
public class ManufaServiceImpl implements ManufaService {

	@Autowired
	private ManufaDAO manufaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Manufacturer> getList() {
		// TODO Auto-generated method stub
		
		return manufaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Manufacturer entity) {
		// TODO Auto-generated method stub
		manufaDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isExistsNum(String num) {
		// TODO Auto-generated method stub
		return manufaDao.isExsitsNum(num);
	}

	@Override
	@Transactional(readOnly=true)
	public Manufacturer getById(long id) {
		// TODO Auto-generated method stub
		return manufaDao.find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isExistsNumSelf(String num) {
		// TODO Auto-generated method stub
		return manufaDao.isExsitsNumSelf(num);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public boolean deleteIds(String ids) {
		
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 Long manId = Long.valueOf(str);
			 if(manufaDao.isServerUse(manId)) {//验证是否有服务器使用
				 return false;
			 }
			 
			 idss[i] = manId;
		}
		
		manufaDao.removeByIds(idss);
		
		return true;
	}
	
	
}
