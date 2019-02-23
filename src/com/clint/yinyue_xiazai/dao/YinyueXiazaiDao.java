package com.clint.yinyue_xiazai.dao;

import java.util.List;
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
	
	//根据id查询记录
	
	public YinyueXiazai getYinyueXiazaiByid(int id);
	
//	查询出所有没有伪原创文章的歌曲记录
	public List<YinyueXiazai> getNoWeiyuanchuang();
	
	//分页查询所有歌曲
	public PageList getPageYinyue(Map<String, Object> tiaojian,int start, int pagesize);
}
