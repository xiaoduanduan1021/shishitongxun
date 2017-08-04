package com.clint.mixin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;

import util.dao.BaseHibernate;




@Repository(value="miXinDao")
public class MiXinDaoImpl extends BaseHibernate implements MiXinDao {
	
	private Logger log = Logger.getLogger(MiXinDaoImpl.class);
	
	//添加
	public MiXin addMiXin(MiXin miXin){
		return (MiXin) this.addObject(miXin);
	}
	//获取
	public MiXin getMiXin(String uuid){
		String hql = "from MiXin where uuid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		return (MiXin) this.getObjectByHqlAndParams(hql, params.toArray());
	}
	//保存
	public MiXin saveMiXin(MiXin miXin){
		return (MiXin) this.updateObject(miXin);
	}
}