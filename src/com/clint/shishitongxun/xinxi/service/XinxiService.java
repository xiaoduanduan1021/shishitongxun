package com.clint.shishitongxun.xinxi.service;

import java.util.List;
import java.util.Map;

import util.page.PageDto;
import util.page.PageList;
import net.sf.json.JSONObject;

import com.clint.shishitongxun.xinxi.model.Guke;
import com.clint.shishitongxun.xinxi.model.Xinxi;



public interface XinxiService {

	public void savePerson(JSONObject jsonObject);
	//获取信息列表
	public PageList getPageXinxi(Map<String, String> tiaojian,PageDto pageDto);
	
	//查询该顾客是否需要存储到数据库
	public String saveGuke(JSONObject jsonObject);
	//获取顾客列表
	//按照有新消息的在上，无新消息的在下
	//分页获取
	public PageList getPageGukeByKefuId(Map<String, Object> tiaojian);
	
	//修改指定客服下的顾客为无新消息
	public Guke updateGukeNoNewXinxi(String httpSessionid,int kefuid);
	//测试访问spring和hibernate
	public List<Xinxi> getAllXinxi();
}
