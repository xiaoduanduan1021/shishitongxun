package com.clint.pinchewang.dao;

import com.clint.pinchewang.model.PinCheXinXi;


public interface PinCheWangDao {
	//查看该qq号今天是否发送过相同信息
	public PinCheXinXi getPinCheXinXiByContent(PinCheXinXi pinCheXinXi);
}
