package com.clint.yinyue_xiazai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.model.Person;

@Controller
@RequestMapping(value = "/yinyue_xiazai")

public class YinyueController {
	
	//进入客户端录入试听地址页面
	@RequestMapping(value = "/yinyue_xiazai_home")
	public String yinyue_xiazai_home() {
		return "/yinyue_xiazai/home/home.jsp";
	}
}
