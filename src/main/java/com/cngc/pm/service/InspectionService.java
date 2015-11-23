package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Inspection;

public interface InspectionService {
	void save(Inspection inspection);
	Inspection getById(Long id);
	List<Inspection> getAll();
}
