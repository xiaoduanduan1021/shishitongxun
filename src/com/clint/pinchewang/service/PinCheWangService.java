package com.clint.pinchewang.service;

import java.util.Map;

import util.page.PageList;

import com.clint.pinchewang.model.PinCheXinXi;


public interface PinCheWangService {

	//查看该qq号今天是否发送过相同信息
	public PinCheXinXi getPinCheXinXiByContent(PinCheXinXi pinCheXinXi);
	
	public PinCheXinXi addPinCheXinXi(PinCheXinXi pxx);
	
	public PinCheXinXi updatePinCheXinXi(PinCheXinXi pxx);
	//按照条件查询信息
	public PageList getPageListPincheXinxi(Map<String, String> tiaojian);
}