package com.clint.shishitongxun.xinxi.controller;


import java.io.IOException;
import java.util.StringTokenizer;

import javax.annotation.Resource;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import test.tt;
import util.qiniu.qiniuGetToken;

import com.clint.shishitongxun.websocketsession.WebSocketSession;
import com.clint.shishitongxun.xinxi.model.Xinxi;
import com.clint.shishitongxun.xinxi.service.XinxiService;
import com.clint.sysuser.model.SysUser;

@Controller
@RequestMapping(value = "/")

public class XinxiController {
	
	private Logger log = Logger.getLogger(XinxiController.class);  
	
	@Autowired
	private  HttpServletRequest request;
	
	@Resource(name = "xinxiService")
	private XinxiService xinxiService;

	//顾客端
	@RequestMapping(value = "/chaochangMessageSend")
	public void chaochangMessageSend(String jsonobject,HttpServletResponse response,HttpSession session) throws IOException, InterruptedException {
		log.info("超长信息发送顾客端");
		log.info(jsonobject);
		log.info("传递给websocket");
		log.info("获取httpsessionid");
		JSONObject jsono = JSONObject.fromObject(jsonobject);
		
		if (jsono.getInt("fasongzhe")==2) {//顾客发送的消息
			String gukeid = jsono.getString("gukeId");
			log.info(gukeid);
			new WebsocketXinxi().onMessage(jsonobject, WebSocketSession.uidToSessionMap.get(gukeid));
		}else{//客服发送的消息
			SysUser sysuser = (SysUser) session.getAttribute("sysUser");
			new WebsocketXinxiKefu().onMessage(jsonobject, WebSocketSession.uidToSessionMap.get(String.valueOf(sysuser.getId())));
		}
		
		response.getWriter().write("ok");
	}
	//七牛获取token
	@RequestMapping(value = "/getQiniuToken")
	public void getQiniuToken(HttpServletResponse response) throws IOException {
		log.info("获取七牛token");
		String token = new qiniuGetToken().getUpToken();
		response.getWriter().write(token);
	}
	
}