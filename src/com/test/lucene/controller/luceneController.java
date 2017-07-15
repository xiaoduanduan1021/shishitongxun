package com.test.lucene.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.service.PersonService;
import com.test.lucene.model.UsrUsers;

@Controller
@RequestMapping(value = "/lucene")
public class luceneController {
	
	@Resource(name = "personService")
	private PersonService personService;

	@RequestMapping(value = "/getUser")
	public void getUser(UsrUsers usrUsers) {
		System.out.println("开始搜索");
		personService.getUser();
	}
}
