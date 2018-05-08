package com.clint.pinchewang.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import util.page.PageList;

import com.clint.pinchewang.model.PinCheXinXi;
import com.clint.pinchewang.service.PinCheWangService;




@Service(value="pinCheWangService")
public class PinCheWangServiceImpl implements PinCheWangService {

	@Resource(name="pinCheWangDao")
	private com.clint.pinchewang.dao.PinCheWangDao PinCheWangDao;

	//查看该qq号今天是否发送过相同信息
	public PinCheXinXi getPinCheXinXiByContent(PinCheXinXi pinCheXinXi){
		return this.PinCheWangDao.getPinCheXinXiByContent(pinCheXinXi);
	}
	
	public PinCheXinXi addPinCheXinXi(PinCheXinXi pxx){
		return this.PinCheWangDao.addPinCheXinXi(pxx);
	}
	
	public PinCheXinXi updatePinCheXinXi(PinCheXinXi pxx){
		return this.PinCheWangDao.updatePinCheXinXi(pxx);
	}
	//按照条件查询信息
	public PageList getPageListPincheXinxi(Map<String, String> tiaojian){
		return this.PinCheWangDao.getPageListPincheXinxi(tiaojian);
	}
}
