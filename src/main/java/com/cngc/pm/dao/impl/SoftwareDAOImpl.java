package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.SoftwareDAO;
import com.cngc.pm.model.Software;

@Repository
public class SoftwareDAOImpl extends BaseDAOImpl<Software, Long> implements
		SoftwareDAO {

}
