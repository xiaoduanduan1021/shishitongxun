package com.clint.mixin.service.impl;


import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import util.string.StringCode;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;
import com.clint.mixin.service.MiXinService;


@Service(value="miXinService")
public class MiXinServiceImpl implements MiXinService {

	@Resource(name="miXinDao")
	private MiXinDao miXinDao;

	@Override
	//添加
	public MiXin addMiXin(MiXin miXin) {
		miXin.setCreationTime(StringCode.getDateTime());
		miXin.setUuid(UUID.randomUUID().toString());
		miXin.setIsRead(0);
		return this.miXinDao.addMiXin(miXin);
	}
	//查看，只可以查看一次
	public MiXin getMiXin(String uuid){
		MiXin miXin = this.miXinDao.getMiXin(uuid);
		if ((int)miXin.getIsRead()==0) {
			miXin.setIsRead(1);
			this.miXinDao.saveMiXin(miXin);
			miXin.setIsRead(0);
			return miXin;
		}else{
			miXin.setContent(null);
			return miXin;
		}
	}
}
