package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.TaskDAO;
import com.cngc.pm.model.Task;

@Repository
public class TaskDAOImpl extends BaseDAOImpl<Task, Long> implements TaskDAO {

}
