package com.cngc.pm.dao;

import org.springframework.stereotype.Repository;

import com.cngc.pm.model.manage.ManagerForm;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

@Repository
public interface ManagerFormDAO extends GenericDAO<ManagerForm,Long> {

}
