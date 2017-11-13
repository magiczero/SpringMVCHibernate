package com.cngc.pm.service.opr;

import java.util.List;

import com.cngc.pm.model.opr.SoftwareWhite;

public interface SoftwareWhiteService {

	void save(SoftwareWhite software);
	SoftwareWhite getById(Long id);
	boolean delById(Long id);
	List<SoftwareWhite> getAll();
}
