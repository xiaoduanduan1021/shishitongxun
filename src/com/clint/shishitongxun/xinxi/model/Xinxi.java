package com.clint.shishitongxun.xinxi.model;


import java.io.Serializable;
//信息类，存储客服和顾客发送的消息
public class Xinxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8430082025690027786L;
	/**
	 * 
	 */
	
	private Integer id;
	private String gukeId;//顾客id
	private Integer kefuId;//客服id
	private String datetime;//时间
	private Integer fasongzhe;//发送者1表示客服2表示顾客
	private Integer stutas;//状态//0或者空表示未发送成功1表示发送成功
	private String neirong;//内容
	
	
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGukeId() {
		return gukeId;
	}
	public void setGukeId(String gukeId) {
		this.gukeId = gukeId;
	}
	public Integer getKefuId() {
		return kefuId;
	}
	public void setKefuId(Integer kefuId) {
		this.kefuId = kefuId;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Integer getFasongzhe() {
		return fasongzhe;
	}
	public void setFasongzhe(Integer fasongzhe) {
		this.fasongzhe = fasongzhe;
	}
	public Integer getStutas() {
		return stutas;
	}
	public void setStutas(Integer stutas) {
		this.stutas = stutas;
	}
	
}
