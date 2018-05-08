package com.clint.pinchewang.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Repository;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;
import com.clint.pinchewang.dao.PinCheWangDao;
import com.clint.pinchewang.model.PinCheXinXi;
import com.clint.pinchewang.util.PincheUtil;

import util.dao.BaseHibernate;
import util.page.PageList;
import util.string.StringCode;




@Repository(value="pinCheWangDao")
public class PinCheWangDaoImpl extends BaseHibernate implements PinCheWangDao {
	
	private Logger log = Logger.getLogger(PinCheWangDaoImpl.class);
	
	//查看该qq号今天是否发送过相同信息
	public PinCheXinXi getPinCheXinXiByContent(PinCheXinXi pinCheXinXi){
		String hql = "from PinCheXinXi where faSongZheQQ='"+pinCheXinXi.getFaSongZheQQ()+"'";
		hql +=" and faSongShiJian like '"+StringCode.getThisYear()+"%'";
		hql +=" and content='"+pinCheXinXi.getContent()+"'";
		return (PinCheXinXi) this.getObjectByHql(hql);
	}
	public PinCheXinXi addPinCheXinXi(PinCheXinXi pxx){
		return (PinCheXinXi) this.addObject(pxx);
	}
	public PinCheXinXi updatePinCheXinXi(PinCheXinXi pxx){
		return (PinCheXinXi) this.updateObject(pxx);
	}
	
	//手机端按照条件查询信息
	public PageList getPageListPincheXinxi(Map<String, String> tiaojian){
		
		Integer yema  = Integer.valueOf(tiaojian.get("yema"));
		String guanjianzi = tiaojian.get("guanjianzi");
		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "from PinCheXinXi where 1=1 ";
		
		if (StringUtils.isNotBlank(guanjianzi)) {
			hql +="and (";
			String or = "";
			String [] guanjianzis  = guanjianzi.split(" ");
			for (String gjz : guanjianzis) {
				hql+=or+" content like '%"+gjz+"%' ";
				or ="or";
			}
			hql +=")";
		}
		
		hql +=" order by faSongShiJian desc";
		System.out.println(hql);
		return this.getPageObjects(hql, params.toArray(), yema*PincheUtil.pagesize, PincheUtil.pagesize);
	}
}