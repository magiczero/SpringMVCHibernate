package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.PersonDAO;
import com.cngc.pm.model.Person;
import com.cngc.pm.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDAO personDAO;
/*
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
*/
	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.save(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.save(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.findAll();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.find(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removeById(id);
	}

}
