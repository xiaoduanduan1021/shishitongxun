package com.clint.mixin.model;



import java.io.Serializable;

//密信，存储用户发送的密信
public class MiXin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443551843975815133L;
	private Integer id;
	private String uuid;//每条信息唯一标识,不可以用id防止用户按照规律获取密信
	private String content;//内容密信正文
	private String creationTime;//创建时间
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
