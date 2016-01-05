package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	/**
	 * 测试Search查询
	 * @return
	 */
	public List<Person> testPersons();
}
