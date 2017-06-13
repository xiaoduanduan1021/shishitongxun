package com.clint.taokewangzhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//套壳网站
@Controller
@RequestMapping(value = "/taoke")
public class TaoKeController {

	@RequestMapping(value = "/toTaoke")
	public String toTaoke(){
		System.out.println("1111111111111111");
		return "taoke/taoke.jsp";
	}
}
