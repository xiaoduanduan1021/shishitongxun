package com.clint.service;

import java.util.List;

import com.clint.model.Person;


public interface PersonService {

public void savePerson(Person p);
	
	public void deletePerson(Person p);
	
	public void updatePerson(Person p);
	
	public Person findPersonById(String id);
	
	public List<Person> findAllPerson();
}
