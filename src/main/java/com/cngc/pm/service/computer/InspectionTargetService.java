package com.cngc.pm.service.computer;

import java.util.List;

import com.cngc.pm.model.computer.InspectionTarget;


public interface InspectionTargetService {

	void save(InspectionTarget inspectionTarget);
	boolean delById(Long targetId);
	InspectionTarget getById(Long targetId);
	List<InspectionTarget> getAll();
	String getJSON();
}
