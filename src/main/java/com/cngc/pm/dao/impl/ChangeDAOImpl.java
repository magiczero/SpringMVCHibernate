package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ChangeDAO;
import com.cngc.pm.model.Change;

@Repository
public class ChangeDAOImpl extends BaseDAOImpl<Change,Long> implements ChangeDAO{

}
