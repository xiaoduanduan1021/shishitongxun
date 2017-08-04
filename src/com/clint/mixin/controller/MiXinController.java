package com.clint.mixin.controller;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import antlr.StringUtils;

import com.clint.mixin.model.MiXin;
import com.clint.mixin.service.MiXinService;
import com.clint.shishitongxun.websocketsession.WebSocketSession;
import com.clint.shishitongxun.xinxi.controller.WebsocketXinxi;
import com.clint.shishitongxun.xinxi.controller.WebsocketXinxiKefu;
import com.clint.sysuser.model.SysUser;



@Controller
@RequestMapping(value = "/")

public class MiXinController {
	
	private Logger log = Logger.getLogger(MiXinController.class);  
	
	@Autowired
	private  HttpServletRequest request;
	
	@Resource(name = "miXinService")
	private MiXinService miXinService;

	
	@RequestMapping(value = "/addMiXin")
	public void addMiXin(MiXin miXin,HttpServletResponse response,HttpSession session) throws IOException {

		log.info("提交");
		log.info(session.getId());
		
		if (org.apache.commons.lang3.StringUtils.isBlank(miXin.getContent())) {
			response.getWriter().write("1");
			return;
		}
		log.info(miXin.getContent().length());
		if (miXin.getContent().length()>1000) {
			response.getWriter().write("2");
			return;
		}
		
		JSONObject json = new JSONObject();
		
		miXin = this.miXinService.addMiXin(miXin);
		json = JSONObject.fromObject(miXin);
		
		response.getWriter().write(json.toString());
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			UUID uuid = UUID.randomUUID();
	        System.out.println(uuid);
		}
	}
}