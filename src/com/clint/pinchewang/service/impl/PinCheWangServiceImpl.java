package com.clint.pinchewang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
}
