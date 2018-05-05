package com.clint.pinchewang.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;
import com.clint.pinchewang.dao.PinCheWangDao;
import com.clint.pinchewang.model.PinCheXinXi;

import util.dao.BaseHibernate;
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
}