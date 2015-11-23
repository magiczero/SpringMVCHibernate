package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.model.Incident;

@Repository
public class IncidentDAOImpl extends BaseDAOImpl<Incident,Long> implements IncidentDAO{

}
