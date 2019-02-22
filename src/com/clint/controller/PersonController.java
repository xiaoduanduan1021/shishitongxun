package com.clint.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.model.Person;
import com.clint.service.PersonService;

@Controller
@RequestMapping(value = "/person")

public class PersonController {
	
	@Resource(name = "personService")
	private PersonService personService;

	@RequestMapping(value = "/savePersonUI")
	public String savePersonUI() {
		return "savePersonForm";
	}

	@RequestMapping(value = "/save")
	public String savePerson(Person p) {
		personService.savePerson(p);
		return "redirect:/person/findAllPerson";
	}

	@RequestMapping(value = "/findAllPerson")
	public String findAllPerson(HttpServletRequest req) {
		
		List<Person> persons = personService.findAllPerson();
		req.setAttribute("persons", persons);
		return "index";
	}

	@RequestMapping(value = "/deletePerson")
	public String deletePerson(Person p) {
		personService.deletePerson(p);

		return "redirect:/person/findAllPerson";
	}

	@RequestMapping(value = "/deletePersons")
	public String deletePersons(String ids) {
		ids = ids.substring(0, ids.length() - 1);

		String[] idss = ids.split(",");
		Person p = new Person();

		for (int i = 0; i < idss.length; i++) {
			p.setId(idss[i]);
			personService.deletePerson(p);
		}
		return "redirect:/person/findAllPerson";
	}

	@RequestMapping(value = "/updatePersonUI")
	public String updatePersonUI(String id, HttpServletRequest req) {
		Person p = personService.findPersonById(id);
		req.setAttribute("p", p);
		return "updatePersonForm";
	}

	@RequestMapping(value = "/updatePerson")
	public String updatePerson(Person p) {
		personService.updatePerson(p);

		return "redirect:/person/findAllPerson";
	}
	@RequestMapping(value = "/testwebSocket")
	public String testwebSocket() {
		return "shishitongxun/guke";
	}
	
}
