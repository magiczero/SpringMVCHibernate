package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.TrainingDAO;
import com.cngc.pm.model.Training;
import com.cngc.pm.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
	private TrainingDAO trainingDao;
	
	@Override
	@Transactional
	public void save(Training training){
		trainingDao.save(training);
	}

	@Override
	@Transactional
	public boolean delById(Long id)
	{
		trainingDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Training getById(Long id){
		return trainingDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Training> getAll()
	{
		return trainingDao.findAll();
	}

}
