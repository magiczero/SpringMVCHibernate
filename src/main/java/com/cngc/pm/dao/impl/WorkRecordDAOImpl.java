package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.WorkRecordDAO;
import com.cngc.pm.model.manage.WorkRecord;

@Repository
public class WorkRecordDAOImpl extends BaseDAOImpl<WorkRecord, Long> implements WorkRecordDAO {

}
