package com.cngc.pm.dao.impl.computer;

import org.springframework.stereotype.Repository;
import com.cngc.pm.dao.computer.ParameterDAO;
import com.cngc.pm.model.computer.Parameter;
import com.cngc.pm.dao.impl.BaseDAOImpl;

@Repository
public class ParameterDAOImpl extends BaseDAOImpl<Parameter,Long> implements ParameterDAO {

}
