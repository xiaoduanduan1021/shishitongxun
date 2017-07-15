package com.clint.dao;

import java.io.IOException;
import java.util.List;

import com.clint.model.Person;


public interface PersonDao {

	public void savePerson(Person p);
	
	public void deletePerson(Person p);
	
	public void updatePerson(Person p);
	
	public Person findPersonById(String id);
	
	public List<Person> findAllPerson();
	//测试lucene高速搜索功能
	public Object getUser () throws  Exception;
}
