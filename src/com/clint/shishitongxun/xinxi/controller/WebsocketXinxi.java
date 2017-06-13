package com.clint.shishitongxun.xinxi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import util.page.PageDto;
import util.page.PageList;
import util.string.StringCode;
import net.sf.json.JSONObject;
import antlr.collections.List;

import com.clint.Aware;
import com.clint.service.PersonService;
import com.clint.shishitongxun.httpsession.GetHttpSessionConfigurator;
import com.clint.shishitongxun.websocketsession.WebSocketSession;
import com.clint.shishitongxun.xinxi.model.Xinxi;
import com.clint.shishitongxun.xinxi.service.XinxiService;
/*
 这个websocket包含
顾客端
注册
发消息
接收消息
*/
@ServerEndpoint(value = "/websocketXinxi",configurator=GetHttpSessionConfigurator.class)
public class WebsocketXinxi{
	private Logger log = Logger.getLogger(WebsocketXinxi.class);
	
	ApplicationContext ac = Aware.context;
	XinxiService xinxiService = (XinxiService) ac.getBean("xinxiService");
	PersonService personService = (PersonService) ac.getBean("personService");

	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		//接收到的消息
		JSONObject jsonobject = JSONObject.fromObject(message);
		String type = jsonobject.getString("type");
		String gukeid = jsonobject.getString("gukeId");
		String kefuid = jsonobject.getString("kefuId");
		
		//注册
		if(type.equals("zhuce")){
			//注册顾客到高速缓存-------------------------------------------------
			//验证顾客id防止冒充客服接入
			if(gukeid.matches("[0-9]+") || gukeid.length()<10){
				jsonobject.put("jieguo", "no");
				session.getBasicRemote().sendText(jsonobject.toString());
				return;
			}
			//用httpsessionid作为id因为这个刷新页面不会变化
			log.info(gukeid);
			WebSocketSession.sessionIdToUidMap.put(session.getId(), gukeid);
			WebSocketSession.uidToSessionMap.put(gukeid, session);
			
			log.info("缓存用户数量"+WebSocketSession.uidToSessionMap.size()+"   "+WebSocketSession.sessionIdToUidMap.size());
			//回复信息注册完成
			jsonobject.put("jieguo", "ok");
			session.getBasicRemote().sendText(jsonobject.toString());
			
			//查询是否有历史消息--------------------------------------------------------
			Map<String, String> tiaojian = new HashMap<String, String>();
			//增加条件，查询指定顾客id和指定客服id的聊天记录分页查询
			tiaojian.put("gukeid", gukeid);
			tiaojian.put("kefuid", kefuid);
			PageDto pageDto = new PageDto();
			PageList pageList = this.xinxiService.getPageXinxi(tiaojian, pageDto);
			/*组合字符串*/		
			JSONObject json = new JSONObject();
			json.put("list", pageList.getDatalist());
			json.put("zongtiaoshu", pageList.getTotalCount());
			json.put("type", "lishijilu");
			log.info(json.toString());			
			session.getBasicRemote().sendText(json.toString());
			//存储顾客到数据库-----------------------------------------查询是否需要存储该顾客到持久层
			xinxiService.saveGuke(jsonobject);
		}
		
		
		//发消息
		if(type.equals("faxiaoxi")){
			//赋值时间
			jsonobject.put("date", StringCode.getDateTime());
		    Session kefuSession = WebSocketSession.uidToSessionMap.get(kefuid);
		    if(kefuSession==null){//客服不在线
		    	jsonobject.put("status", 0);
		    }else{
		    	
		    	kefuSession.getBasicRemote().sendText(jsonobject.toString());
		    	jsonobject.put("status", 1);
		    }
		    
		    //存储发送的消息
		    jsonobject.put("fasongzhe", 2);
		    xinxiService.savePerson(jsonobject);
		    //回调信息
		    jsonobject.put("type", "faxiaoxihuidiao");
		    session.getBasicRemote().sendText(jsonobject.toString());
		    
		}
		
		//历史消息查询分页
		if(type.equals("lishixiaoxi")){
			Map<String, String> tiaojian = new HashMap<String, String>();
			//增加条件，查询指定顾客id和指定客服id的聊天记录分页查询
			tiaojian.put("gukeid", gukeid);
			tiaojian.put("kefuid", kefuid);
			PageDto pageDto = new PageDto();
			pageDto.setPageNumber(jsonobject.getInt("yema"));
			PageList pageList = this.xinxiService.getPageXinxi(tiaojian, pageDto);
			/*组合字符串*/		
			JSONObject json = new JSONObject();
			json.put("list", pageList.getDatalist());
			json.put("zongtiaoshu", pageList.getDatalist().size());
			json.put("type", "lishijilu");
			log.info(json.toString());
			session.getBasicRemote().sendText(json.toString());
		}
    }
	
	@OnOpen
    public void onOpen (Session session,EndpointConfig config) {
//		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        log.info("客户端连接  ");
    }

    @OnClose
    public void onClose (Session session) {
    	log.info("连接关闭");
    	//清空两个session缓存
    	String httpsessionid = WebSocketSession.sessionIdToUidMap.get(session.getId());
    	WebSocketSession.uidToSessionMap.remove(httpsessionid);
    	WebSocketSession.sessionIdToUidMap.remove(session.getId());
    }
    
}
