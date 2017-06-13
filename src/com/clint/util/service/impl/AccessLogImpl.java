package com.clint.util.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clint.util.dao.AccessLogDao;
import com.clint.util.model.AccessLog;
import com.clint.util.model.AccessUser;
import com.clint.util.service.AccessLogService;

@Service(value = "accessLogService")
public class AccessLogImpl implements AccessLogService {

	@Resource(name = "accessLogDao")
	private AccessLogDao accessLogDao;

	public AccessUser addAccessUser(AccessUser accessUser) {
		return this.accessLogDao.addAccessUser(accessUser);
	}
	public AccessLog addAccessLog(AccessLog accessLog){
		return this.accessLogDao.addAccessLog(accessLog);
	}
	public AccessLog getAccessLogById(int id){
		return this.accessLogDao.getAccessLogById(id);
	}
	public AccessLog updateAccessLog(AccessLog accessLog){
		return this.accessLogDao.updateAccessLog(accessLog);
	}
	public AccessLog getAccessLogByASid(String accessSessionId){
		return this.accessLogDao.getAccessLogByASid(accessSessionId);
	}
	public AccessLog getAccessLogByYemianid(String yemianid){
		return this.accessLogDao.getAccessLogByYemianid(yemianid);
	}
}
