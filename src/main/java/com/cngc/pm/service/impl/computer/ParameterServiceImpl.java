package com.cngc.pm.service.impl.computer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.computer.ParameterDAO;
import com.cngc.pm.model.computer.Parameter;
import com.cngc.pm.service.computer.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterDAO parameterDao;
	
	@Override
	@Transactional
	public void save(int ninterval) {
		// TODO 自动生成的方法存根
		Parameter para = getParamter();
		para.setInterval(ninterval);
		parameterDao.save(para);
	}

	@Override
	public Parameter getParamter() {
		// TODO 自动生成的方法存根
		List<Parameter> list = parameterDao.findAll();
		if(list.size()>0)
			return list.get(0);
		else
		{
			Parameter para = new Parameter();
			//默认是120秒
			para.setInterval(120000);
			return para;
		}
	}

}
