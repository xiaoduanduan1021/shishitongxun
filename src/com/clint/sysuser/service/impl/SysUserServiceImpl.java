package com.clint.sysuser.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clint.sysuser.dao.SysUserDao;
import com.clint.sysuser.model.SysUser;
import com.clint.sysuser.service.SysUserService;


@Service(value="sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource(name="sysUserDao")
	private SysUserDao sysUserDao;
	
	//获取指定登录名的用户
	public SysUser getSysUserByLoginName(String loginName){
		return sysUserDao.getSysUserByLoginName(loginName);
	}
	//添加用户
	public SysUser addSysUser(SysUser sysUser){
		return this.sysUserDao.addSysUser(sysUser);
	}
	//删除账户
	public void deleteSysUser(SysUser sysUser){
		this.sysUserDao.deleteSysUser(sysUser);
	}
	//获取账户根据id
	public SysUser getSysUserByid(int id){
		return sysUserDao.getSysUserByid(id);
	}
	//修改账户
	public SysUser updateSysUser(SysUser sysUser){
		return sysUserDao.updateSysUser(sysUser);
	}
	//根据邮箱获取账户
	public List<SysUser> getSysUsersByEmail(String email){
		return sysUserDao.getSysUsersByEmail(email);
	}
}
