package com.clint.dao;

import java.util.List;

import com.clint.model.Person;


public interface PersonDao {

	public void savePerson(Person p);
	
	public void deletePerson(Person p);
	
	public void updatePerson(Person p);
	
	public Person findPersonById(String id);
	
	public List<Person> findAllPerson();
	
}
