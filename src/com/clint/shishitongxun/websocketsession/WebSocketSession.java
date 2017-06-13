package com.clint.shishitongxun.websocketsession;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

//存储websocketsession会话
public class WebSocketSession {
	
	/**
	存储sessionid到用户id
	存储用户id到session
	如果是客户uid存储httpsessionid目前是这样将来可能要改为机器码或者其他
	如果是客服uid存储userid登陆的用户id
	 * */
	public static Map<String, Session> uidToSessionMap = new HashMap<String, Session>();
	public static Map<String, String> sessionIdToUidMap = new HashMap<String, String>();
}
