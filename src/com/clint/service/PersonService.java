package com.clint.service;

import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.clint.model.Person;


public interface PersonService {

public void savePerson(Person p);
	
	public void deletePerson(Person p);
	
	public void updatePerson(Person p);
	
	public Person findPersonById(String id);
	
	public List<Person> findAllPerson();
	//测试lucene高速搜索功能
	public Object getUser();
	
	public List<String> Searche(String text) throws Exception;
}
