package com.clint.mixin.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;

import util.dao.BaseHibernate;




@Repository(value="miXinDao")
public class MiXinDaoImpl extends BaseHibernate implements MiXinDao {
	
	private Logger log = Logger.getLogger(MiXinDaoImpl.class);
	
	public MiXin addMiXin(MiXin miXin){
		return (MiXin) this.addObject(miXin);
	}
}