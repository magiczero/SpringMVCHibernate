package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Change;

public interface ChangeService {
	
	void save(Change change);
	boolean delById(Long id);
	Change getById(Long id);
	List<Change> getAll();

}
