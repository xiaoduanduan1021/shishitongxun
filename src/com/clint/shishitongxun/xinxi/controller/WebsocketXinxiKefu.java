package com.clint.shishitongxun.xinxi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
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

import com.clint.Aware;
import com.clint.service.PersonService;
import com.clint.shishitongxun.httpsession.GetHttpSessionConfigurator;
import com.clint.shishitongxun.websocketsession.WebSocketSession;
import com.clint.shishitongxun.xinxi.service.XinxiService;
import com.clint.sysuser.model.SysUser;
/*
 这个websocket包含
客服端
注册
发消息
接收消息
*/
@ServerEndpoint(value = "/websocketXinxiKefu",configurator=GetHttpSessionConfigurator.class)
public class WebsocketXinxiKefu{
	
	private Logger log = Logger.getLogger(WebsocketXinxiKefu.class);
	
	ApplicationContext ac = Aware.context;
	XinxiService xinxiService = (XinxiService) ac.getBean("xinxiService");
	PersonService personService = (PersonService) ac.getBean("personService");
	
	/**
	存储sessionid到用户id
	存储用户id到session
	如果是客户uid存储httpsessionid目前是这样将来可能要改为机器码或者其他
	如果是客服uid存储userid登陆的用户id
	 * */

	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		JSONObject jsonobject = JSONObject.fromObject(message);
		jsonobject.put("kefuId", WebSocketSession.sessionIdToUidMap.get(session.getId()));
		String kefuId = jsonobject.getString("kefuId");
		int sysUserId = Integer.valueOf(kefuId);
		
		String type = jsonobject.getString("type");
		//发消息
		if(type.equals("faxiaoxi")){
			//赋值时间
			jsonobject.put("date", StringCode.getDateTime());
		    Session gukeSession = WebSocketSession.uidToSessionMap.get(jsonobject.get("gukeId"));
		    //发消息
		    if(gukeSession == null){//对方不在线
		    	jsonobject.put("status", 0);
		    }else{
		    	jsonobject.put("status", 1);
		    	gukeSession.getBasicRemote().sendText(jsonobject.toString());
		    }
		    
		    //存储发送的消息
		    jsonobject.put("fasongzhe", 1);
		    xinxiService.savePerson(jsonobject);
		    
		    
		    //发消息回调
		    jsonobject.put("type", "faxiaoxihuidiao");
		    session.getBasicRemote().sendText(jsonobject.toString());
		    
		    
		}
		//获取顾客列表分页
		if(type.equals("gukeliebiao")){
			Map<String, Object> tiaojian = new HashMap<>();
			tiaojian.put("yema", jsonobject.getInt("yema"));
			tiaojian.put("pageSize", 10);
			tiaojian.put("kefuId", sysUserId);
			PageList pagelist = xinxiService.getPageGukeByKefuId(tiaojian);
			/*组合字符串*/		
			JSONObject json = new JSONObject();
			json.put("list", pagelist.getDatalist());
			json.put("zongtiaoshu", pagelist.getDatalist().size());
			json.put("type", "kehuliebiao");
			log.info(json.toString());			
			session.getBasicRemote().sendText(json.toString());
		}
		//获取历史消息
		if(type.equals("lishixiaoxi")){
			Map<String, String> tiaojian = new HashMap<String, String>();
			//增加条件，查询指定顾客id和指定客服id的聊天记录分页查询
			tiaojian.put("gukeid", jsonobject.getString("gukeId"));
			tiaojian.put("kefuid", kefuId);
			PageDto pageDto = new PageDto();
			pageDto.setPageNumber(jsonobject.getInt("yema"));
			PageList pageList = this.xinxiService.getPageXinxi(tiaojian, pageDto);
			/*组合字符串*/		
			JSONObject json = new JSONObject();
			json.put("list", pageList.getDatalist());
			json.put("zongtiaoshu", pageList.getDatalist().size());
			json.put("type", "lishijilu");
			json.put("gukeId", jsonobject.getString("gukeId"));
			log.info(json.toString());
			session.getBasicRemote().sendText(json.toString());
			//设置该顾客为没有新消息
			this.xinxiService.updateGukeNoNewXinxi(jsonobject.getString("gukeId"), sysUserId);
		}
    }
	
	@OnOpen
    public void onOpen (Session session,EndpointConfig config) throws IOException {
		JSONObject jsonobject = new JSONObject();
		
		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		//客服端链接的时候需要验证httpsession
		//获取httpSession判断是否已经登录
		SysUser sysUser = (SysUser) httpSession.getAttribute("sysUser");
		if(sysUser==null){
			jsonobject.put("type", "noLogin");
			session.getBasicRemote().sendText(jsonobject.toString());
			log.info("请登录");
		}else{
			//使用httpSessionId注册
			//注册过程--------------------------------------------
			//用httpsessionid作为id因为这个刷新页面不会变化
			
			log.info("客服id："+sysUser.getId());
			
			WebSocketSession.sessionIdToUidMap.put(session.getId(), String.valueOf(sysUser.getId()));
			WebSocketSession.uidToSessionMap.put(String.valueOf(sysUser.getId()), session);
			
			log.info("缓存用户数量"+WebSocketSession.uidToSessionMap.size()+"   "+WebSocketSession.sessionIdToUidMap.size());
			//回复信息注册完成
			jsonobject.put("type", "zhuce");
			jsonobject.put("jieguo", "ok");
			session.getBasicRemote().sendText(jsonobject.toString());
			

			//查询顾客列表--------------------------------------------
			//顾客列表中有新消息的在上面,没有新消息的在下面
			
			Map<String, Object> tiaojian = new HashMap<>();
			tiaojian.put("yema", 1);
			tiaojian.put("pageSize", 10);
			tiaojian.put("kefuId", sysUser.getId());
			PageList pagelist = xinxiService.getPageGukeByKefuId(tiaojian);
			/*组合字符串*/		
			JSONObject json = new JSONObject();
			json.put("list", pagelist.getDatalist());
			json.put("zongtiaoshu", pagelist.getTotalCount());
			json.put("type", "kehuliebiao");
			log.info(json.toString());			
			session.getBasicRemote().sendText(json.toString());
		}
		
        log.info("客服端连接  ");
    }

    @OnClose
    public void onClose (Session session) {
    	log.info("客服端连接关闭");
    	//清空两个session缓存
    	String userid = WebSocketSession.sessionIdToUidMap.get(session.getId());
    	WebSocketSession.uidToSessionMap.remove(userid);
    	WebSocketSession.sessionIdToUidMap.remove(session.getId());
    }
    
}
