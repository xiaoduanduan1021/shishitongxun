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
	
	
}