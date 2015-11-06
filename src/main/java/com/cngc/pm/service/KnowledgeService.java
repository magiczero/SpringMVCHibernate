package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Knowledge;

public interface KnowledgeService {

	void save(Knowledge knowledge);
	
	boolean delByIds(String ids);
	
	Knowledge getById(Long id);
	
	List<Knowledge> getAll();

}
