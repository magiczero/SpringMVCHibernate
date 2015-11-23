package com.cngc.pm.service;

import java.util.List;
import com.cngc.pm.model.Incident;

public interface IncidentService {

	void save(Incident event);
	boolean delByIds(String ids);
	boolean delById(Long id);
	Incident getById(Long id);
	List<Incident> getAll();
}
