package com.clint.util.dao.impl;

import org.springframework.stereotype.Repository;

import util.dao.BaseHibernate;

import com.clint.util.dao.AccessLogDao;
import com.clint.util.model.AccessLog;
import com.clint.util.model.AccessUser;

@Repository(value = "accessLogDao")
public class AccessLogDaoImpl extends BaseHibernate implements AccessLogDao {

	public AccessUser addAccessUser(AccessUser accessUser) {
		return (AccessUser) this.addObject(accessUser);
	}
	public AccessLog addAccessLog(AccessLog accessLog){
		return (AccessLog) this.addObject(accessLog);
	}
	public AccessLog getAccessLogById(int id){
		return (AccessLog) this.getObject(AccessLog.class, id);
	}
	public AccessLog updateAccessLog(AccessLog accessLog){
		return (AccessLog) this.updateObject(accessLog);
	}
	public AccessLog getAccessLogByASid(String accessSessionId){
		String hql= " from AccessLog where accessSessionId='"+accessSessionId+"'";
		return (AccessLog) this.getObjectByHql(hql);
	}
	public AccessLog getAccessLogByYemianid(String yemianid){
		String hql= " from AccessLog where yemianid='"+yemianid+"'";
		return (AccessLog) this.getObjectByHql(hql);
	}
}
