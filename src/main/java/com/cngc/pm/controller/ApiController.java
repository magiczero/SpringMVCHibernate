package com.cngc.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cngc.pm.model.Person;
import com.cngc.pm.service.PersonService;

@RestController
public class ApiController {
	
	@Autowired(required = true)
	PersonService personService;

	@RequestMapping(value="/api/person", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> listAllPersons() {
		List<Person> persons = personService.listPersons();
		if(persons.isEmpty()) {
			 return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/api/cmdb/change", method = RequestMethod.POST)
	 public ResponseEntity<Void> cmdbChange(@RequestParam(required = true) String changeType,
			 UriComponentsBuilder ucBuilder)   {
		 System.out.println("phoneNumber=" + changeType);
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand("1").toUri());
		 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
}
