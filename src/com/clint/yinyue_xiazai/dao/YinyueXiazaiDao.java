package com.clint.yinyue_xiazai.dao;

import java.util.Map;

import com.clint.yinyue_xiazai.model.YinyueXiazai;

import util.page.PageList;

public interface YinyueXiazaiDao {

	// 添加音乐下载记录
	public void saveYinyueXiazai(YinyueXiazai yinyueXiazai);

	// 查询最近的100条
	public PageList getPageYinyueXiazaiS(Map<String, Object> tiaojian);
	
	//查询未处理的最近的10条
	public PageList getPageYinyue_weichuli();
	
	//修改音乐下载记录
	public void updataYinyueXiazai(YinyueXiazai yinyueXiazai);
	//查询音乐下载记录
	public YinyueXiazai getYinyueXiazai(Integer id);
}
