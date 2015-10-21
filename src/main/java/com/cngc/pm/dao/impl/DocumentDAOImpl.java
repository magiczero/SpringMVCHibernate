package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.DocumentDAO;
import com.cngc.pm.model.Document;

@Repository
public class DocumentDAOImpl extends BaseDAOImpl<Document, Long> implements DocumentDAO {

}
