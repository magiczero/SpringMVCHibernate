package com.cngc.pm.dao;

import org.hibernate.Session;

import com.cngc.pm.model.Person;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface PersonDAO extends GenericDAO<Person, Integer> {
/*
	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	*/
	
	public Session getSession();
}
