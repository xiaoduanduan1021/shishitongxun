package com.clint.util.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.string.UrlConnection;

@Controller
@RequestMapping(value = "/")

public class IpController {
	 private Logger log = Logger.getLogger(IpController.class);
	@Autowired
	private  HttpServletRequest request;

	/**
	 * 返回该请求的发出客户端 的ip地址和地区
	 * 返回json字符
	 	* 
	 	1. 请求接口（GET）：
		/service/getIpInfo.php?ip=[ip地址字串]
		2. 响应信息：
		（json格式的）国家 、省（自治区或直辖市）、市（县）、运营商
		3. 返回数据格式：
		{"code":0,"data":{"ip":"210.75.225.254","country":"\u4e2d\u56fd","area":"\u534e\u5317",
		"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
		"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
		"county_id":"-1","isp_id":"100017"}}
		其中code的值的含义为，0：成功，1：失败。
	 * 
	 * **/
	@RequestMapping(value = "/getIpByTaobao")
	public void getIpByTaobao(HttpServletResponse response) throws IOException {
		log.info("获取ip");
		String ip = request.getRemoteAddr();
		log.info("ip:"+ip);
		String iptaobao = new UrlConnection().getIpRegionByTaobao(ip);
		response.getWriter().write(iptaobao);
		log.info(iptaobao);
		return;
	}
	
	
}