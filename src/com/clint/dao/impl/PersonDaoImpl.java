package com.clint.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.clint.dao.PersonDao;
import com.clint.model.Person;


@Repository(value="personDao")
public class PersonDaoImpl implements PersonDao {
	
	
	@Autowired
	private  HibernateTemplate hibernateTemplate;

	public void savePerson(Person p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	public void deletePerson(Person p) {
		hibernateTemplate.delete(p);
	}

	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	public Person findPersonById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Person> findAllPerson() {
		
		List l1 = (List) hibernateTemplate.find(" from Person");
		List l = (List) hibernateTemplate.find(" from Xinxi");
		return hibernateTemplate.find(" from Person");
	}
	
}
