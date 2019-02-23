package com.clint.yinyue_xiazai.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;

import util.dao.BaseHibernate;
import util.page.PageList;


@Repository(value="yinyueXiazaiDao")
public class YinyueXiazaiDaoImpl extends BaseHibernate implements YinyueXiazaiDao {
	
	//添加音乐下载记录
	public void saveYinyueXiazai(YinyueXiazai yinyueXiazai) {
		this.addObject(yinyueXiazai);
	}
	//修改音乐下载记录
	public void updataYinyueXiazai(YinyueXiazai yinyueXiazai) {
		this.updateObject(yinyueXiazai);
	}
	//查询音乐下载记录
	public YinyueXiazai getYinyueXiazai(Integer id) {
		return (YinyueXiazai) this.getObject(YinyueXiazai.class, id);
	}
	
	
	//查询最近的100条
	public PageList getPageYinyueXiazaiS(Map<String, Object> tiaojian) {

		String uuid = (String) tiaojian.get("uuid");
		
		List<Object> params = new ArrayList<Object>();//存储查询参数
		String sql = " from YinyueXiazai where uuid=?";
		params.add(uuid);
		sql+=" order by datetime desc";
		
		PageList pageList = this.getPageObjects(sql, params.toArray(),
					0, 100);
		return pageList;
	}
	//查询未处理的最近的10条
	public PageList getPageYinyue_weichuli() {
		
		List<Object> params = new ArrayList<Object>();//存储查询参数
		String sql = " from YinyueXiazai where status is null or status=0";
		
		PageList pageList = this.getPageObjects(sql, params.toArray(),
				0, 3);
		return pageList;
	}
	
	
	//根据id查询记录
	
	public YinyueXiazai getYinyueXiazaiByid(int id){
		return (YinyueXiazai) this.getObject(YinyueXiazai.class, id);
	}
	
	
//	查询出所有没有伪原创文章的歌曲记录
	public List<YinyueXiazai> getNoWeiyuanchuang() {
		
		
		String sql = " from YinyueXiazai where weiYuanChuang is null";
		
		return this.getObjects(sql);
	}
	
	//分页查询所有歌曲
	public PageList getPageYinyue(Map<String, Object> tiaojian,int start, int pagesize) {

		List<Object> params = new ArrayList<Object>();//存储查询参数
		String sql = " from YinyueXiazai ";
		String c_sql = "select count(id) "+sql;
		int totalCount = ((Long)this.getObjects(c_sql).iterator().next()).intValue();
	    PageList pagelist = new PageList((ArrayList)getPageObjects(sql, params.toArray(), (start-1)*pagesize, pagesize).getDatalist(), (start-1)*pagesize, pagesize, totalCount);		
		return pagelist;
		
	}
}