package com.clint.util.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.shishitongxun.xinxi.service.XinxiService;
import com.clint.util.model.AccessLog;
import com.clint.util.model.AccessUser;
import com.clint.util.service.AccessLogService;

import util.string.StringCode;
import util.string.UrlConnection;

@Controller
@RequestMapping(value = "/")
public class AccessLogController {
	private Logger log = Logger.getLogger(AccessLogController.class);
	@Autowired
	private HttpServletRequest request;

	@Resource(name = "accessLogService")
	private AccessLogService accessLogService;

	/*
	 * 记录所有网站的访问量不限制网站 记录离开时间并记录，计算停留时间 未开发完成 存储访问人id
	 * 
	 * 
	 * 参数： one 1表示第一次访问 2表示再次访问 in 1表示进入页面2表示离开页面
	 */

	@RequestMapping(value = "/addAllComEvent")
	public void addAllComEvent(Integer one, AccessLog accessLog, Integer in, HttpServletResponse response,Long thisms,Integer sysuser) throws IOException {
		System.out.println("接收消息");
		// 跨域问题
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject jsonobj = new JSONObject();
		// 判断如果是第一次访问则存储用户身份并获取ip信息
		if (one == 1) {
			System.out.println("第一次访问");
			AccessUser accessUser = new AccessUser();
			String ip = request.getRemoteAddr();
			String iptaobao = new UrlConnection().getIpRegionByTaobao(ip);
			System.out.println("访问者地区信息：" + iptaobao);
			accessUser.setIp(ip);
			JSONObject ipJsonobject = JSONObject.fromObject(iptaobao);
			if (ipJsonobject.getString("code").equals("0")) {

				String data = ipJsonobject.getString("data");
				JSONObject dataJsonobject = JSONObject.fromObject(data);
				
				accessUser.setCountry(dataJsonobject.getString("country"));
				accessUser.setCountry_id(dataJsonobject.getString("country_id"));
				accessUser.setArea(dataJsonobject.getString("area"));
				accessUser.setArea_id(dataJsonobject.getString("area_id"));
				accessUser.setRegion(dataJsonobject.getString("region"));
				accessUser.setRegion_id(dataJsonobject.getString("region_id"));
				accessUser.setCity(dataJsonobject.getString("city"));
				accessUser.setCity_id(dataJsonobject.getString("city_id"));
				accessUser.setCounty(dataJsonobject.getString("county"));
				accessUser.setCounty_id(dataJsonobject.getString("county_id"));
				accessUser.setIsp(dataJsonobject.getString("isp"));
			}
			accessUser.setAccessSessionId(accessLog.getAccessSessionId());
			
			String Agent = request.getHeader("User-Agent");
			System.out.println("系统信息："+Agent);
			accessUser.setSystemInformation(Agent);
			accessUser.setCreateTime(StringCode.getDateTime());
			accessUser.setSysuser(sysuser);
			accessLogService.addAccessUser(accessUser);
		}
		//判断是进入页面还是离开
		if (in == 1) {
			//如果是进入页面则创建访问记录
			accessLog.setStartTime(StringCode.getDateTime());
			accessLog.setStartTimeS(thisms);
			accessLog.setUrl(request.getHeader("referer"));
			accessLogService.addAccessLog(accessLog);
			jsonobj.put("accessLog", accessLog);
		}else{
			//如果是离开页面则计算停留时间并修改
			AccessLog dbaccesslog = this.accessLogService.getAccessLogByYemianid(accessLog.getYemianid());
			dbaccesslog.setEndTime(StringCode.getDateTime());
			dbaccesslog.setEndTimeS(thisms);
			dbaccesslog.setResidenceTime(thisms-dbaccesslog.getStartTimeS());
			//修改保存
			accessLogService.updateAccessLog(dbaccesslog);
		}
		


		jsonobj.put("type", "ok");
		response.getWriter().write(jsonobj.toString());
	}

}