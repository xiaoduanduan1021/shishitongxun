package com.clint.mixin.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;
import com.clint.mixin.service.MiXinService;


@Service(value="miXinService")
public class MiXinServiceImpl implements MiXinService {

	@Resource(name="miXinDao")
	private MiXinDao miXinDao;

	@Override
	public MiXin addMiXin(MiXin miXin) {
		
		return this.miXinDao.addMiXin(miXin);
	}
	
}
