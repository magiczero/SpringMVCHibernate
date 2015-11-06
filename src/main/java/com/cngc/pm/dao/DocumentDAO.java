package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Document;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface DocumentDAO extends GenericDAO<Document, Long> {

	List<Document> getListBySelfAndOpen(Long userId);

	List<Document> getListBySelfAndOpenAndStyle(Long userId, Long styleId);

	List<Document> getListBySelf(Long userId);
}
