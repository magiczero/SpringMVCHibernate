package com.cngc.pm.dao.impl.computer;

import org.springframework.stereotype.Repository;
import com.cngc.pm.dao.computer.InspectionTaskDAO;
import com.cngc.pm.dao.impl.BaseDAOImpl;
import com.cngc.pm.model.computer.InspectionTask;

@Repository
public class InspectionTaskDAOImpl extends BaseDAOImpl<InspectionTask,Long> implements InspectionTaskDAO{

}
