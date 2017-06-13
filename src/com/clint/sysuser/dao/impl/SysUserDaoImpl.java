package com.clint.sysuser.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import util.dao.BaseHibernate;

import com.clint.sysuser.dao.SysUserDao;
import com.clint.sysuser.model.SysUser;


@Repository(value="sysUserDao")
public class SysUserDaoImpl extends BaseHibernate implements SysUserDao {

	//获取指定登录名的用户
	public SysUser getSysUserByLoginName(String loginName){
		String hql = " from SysUser where loginName='"+loginName+"'";
		return (SysUser) this.getObjectByHql(hql);
	}
	
	//添加用户
	public SysUser addSysUser(SysUser sysUser){
		return (SysUser) this.addObject(sysUser);
	}
	//删除账户
	public void deleteSysUser(SysUser sysUser){
		this.delObject(sysUser);
	}
	//获取账户根据id
	public SysUser getSysUserByid(int id){
		return (SysUser) this.getObject(SysUser.class, id);
	}
	//修改账户
	public SysUser updateSysUser(SysUser sysUser){
		return (SysUser) this.updateObject(sysUser);
	}
	//根据邮箱获取账户
	public List<SysUser> getSysUsersByEmail(String email){
		List<Object> param = new ArrayList<Object>();
		String hql = " from SysUser where email=?";
		param.add(email);
		return this.getObjects(hql, param.toArray());
	}
}
