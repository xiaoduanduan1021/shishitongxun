package com.clint.shishitongxun.xinxi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;












import util.dao.BaseHibernate;
import util.page.PageDto;
import util.page.PageList;

import com.clint.shishitongxun.xinxi.dao.XinxiDao;
import com.clint.shishitongxun.xinxi.model.Guke;
import com.clint.shishitongxun.xinxi.model.Xinxi;


@Repository(value="xinxiDao")
public class XinxiDaoImpl extends BaseHibernate implements XinxiDao {
	
	private Logger log = Logger.getLogger(XinxiDaoImpl.class);


	public void savePerson(Xinxi x) {
		this.addObject(x);
	}
	//获取信息列表
	public PageList getPageXinxi(Map<String, String> tiaojian,PageDto pageDto) {

		List<Object> params = new ArrayList<Object>();//存储查询参数
		StringBuffer hql = new StringBuffer("from Xinxi as a where 1=1 ");
		String sql = this.setCondition(tiaojian, params, hql, pageDto);
		log.info("sql:"+sql);
		log.info("获取的页码:"+pageDto.getPageNumber());
		
		PageList pageList = this.getPageObjects(sql, params.toArray(),
					(pageDto.getPageNumber()-1)*pageDto.getPageSize(), pageDto.getPageSize());
		return pageList;
	}
	//获取信息列表组装条件
	private String setCondition(Map<String, String> tiaojian,List<Object> params,
			StringBuffer hql, PageDto pageDto) {
		
			//增加条件顾客id
			hql.append(" and gukeId = ?");
			params.add(tiaojian.get("gukeid"));
			//增加条件客服id
			hql.append(" and kefuId = ?");
			params.add(Integer.valueOf(tiaojian.get("kefuid")));
			
			//将先发的放在上面，后发的放在下面
			//按照时间倒叙
			hql.append(" order by datetime desc");
			return hql.toString();
	}
	//根据gukeid（httpsessionid）获取顾客
	public Guke getGukeByHttpSessionid(String httpSessionid,int kefuid){
		return (Guke) this.getObjectByHql(" from Guke where httpSessionId='"+httpSessionid+"' and kefuId="+kefuid);
	}
	//添加顾客
	public Guke addGuke(Guke guke){
		return (Guke) this.addObject(guke);
	}
	//修改顾客
	public Guke updateGuke(Guke guke){
		return (Guke) this.updateObject(guke);
	}
	
	//获取顾客列表
	//按照有新消息的在上，无新消息的在下
	//分页获取
	public PageList getPageGukeByKefuId(Map<String, Object> tiaojian) {

		int yema = (int) tiaojian.get("yema");
		int pagesize = (int) tiaojian.get("pageSize");
		int kefuid = (int) tiaojian.get("kefuId");
		
		List<Object> params = new ArrayList<Object>();//存储查询参数
		String sql = " from Guke where kefuId="+kefuid+" order by newXinxi desc,zhuceShijian desc";
		
		log.info("sql:"+sql);
		log.info("获取的页码:"+yema);
		
		PageList pageList = this.getPageObjects(sql, params.toArray(),
					(yema-1)*pagesize, pagesize);
		return pageList;
	}
	//测试访问spring和hibernate
	public List<Xinxi> getAllXinxi(){
		log.info("测试spring");
		return this.getObjects("from Xinxi");
	}
}