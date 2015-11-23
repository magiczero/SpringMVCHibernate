package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Training;

public interface TrainingService {

	void save(Training training);
	boolean delById(Long id);
	Training getById(Long id);
	List<Training> getAll();
}
