package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.RecordDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.model.Record;

@Repository
public class RecordDAOImpl extends BaseDAOImpl<Record,Long> implements RecordDAO{

}
