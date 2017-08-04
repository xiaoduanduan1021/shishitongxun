package com.clint.mixin.dao;

import com.clint.mixin.model.MiXin;

public interface MiXinDao {
	public MiXin addMiXin(MiXin miXin);

	public MiXin getMiXin(String uuid);

	// 保存
	public MiXin saveMiXin(MiXin miXin);
}
