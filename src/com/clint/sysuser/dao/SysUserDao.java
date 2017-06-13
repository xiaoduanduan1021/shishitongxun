package com.clint.sysuser.dao;

import java.util.List;

import com.clint.sysuser.model.SysUser;




public interface SysUserDao {
	//获取指定登录名的用户
	public SysUser getSysUserByLoginName(String loginName);
	//添加用户
	public SysUser addSysUser(SysUser sysUser);
	//删除账户
	public void deleteSysUser(SysUser sysUser);
	//获取账户根据id
	public SysUser getSysUserByid(int id);
	//修改账户
	public SysUser updateSysUser(SysUser sysUser);
	//根据邮箱获取账户
	public List<SysUser> getSysUsersByEmail(String email);
}
