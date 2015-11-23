package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.Relation;

public interface RelationService {

	void save(Relation relation);

	boolean delById(String id);

	Relation getById(String id);

	List<Relation> getAll();
}
