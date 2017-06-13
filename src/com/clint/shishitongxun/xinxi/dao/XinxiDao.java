package com.clint.shishitongxun.xinxi.dao;

import java.util.List;
import java.util.Map;

import util.page.PageDto;
import util.page.PageList;

import com.clint.shishitongxun.xinxi.model.Guke;
import com.clint.shishitongxun.xinxi.model.Xinxi;



public interface XinxiDao {

	public void savePerson(Xinxi x);
	//获取信息列表
	public PageList getPageXinxi(Map<String, String> tiaojian,PageDto pageDto);
	//根据gukeid（httpsessionid）获取顾客
	public Guke getGukeByHttpSessionid(String httpSessionid,int kefuid);
	//添加顾客
	public Guke addGuke(Guke guke);
	//修改顾客
	public Guke updateGuke(Guke guke);
	//获取顾客列表
	//按照有新消息的在上，无新消息的在下
	//分页获取
	public PageList getPageGukeByKefuId(Map<String, Object> tiaojian);
	//测试访问spring和hibernate
	public List<Xinxi> getAllXinxi();
}
