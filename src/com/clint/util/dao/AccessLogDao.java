package com.clint.util.dao;

import com.clint.util.model.AccessLog;
import com.clint.util.model.AccessUser;

public interface AccessLogDao {
	public AccessUser addAccessUser(AccessUser accessUser);
	public AccessLog addAccessLog(AccessLog accessLog);
	public AccessLog getAccessLogById(int id);
	public AccessLog updateAccessLog(AccessLog accessLog);
	public AccessLog getAccessLogByASid(String accessSessionId);
	public AccessLog getAccessLogByYemianid(String yemianid);
}
