package com.clint.xiamipinglun.mobel;

import java.io.Serializable;

//虾米评论检测记录
public class XiamiJianceJilu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3636968445461698179L;
	private Integer id;
	private String date;//检测时间
	private String enddate;//检测结束时间
	
	private Integer xiaodan;//小单第一个数
	private Integer chaoshi;//小单超时个数
	private Integer dadan;//大单是否第一
	private Integer dadanchaoshi;//大单是否超时
	
	
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getXiaodan() {
		return xiaodan;
	}
	public void setXiaodan(Integer xiaodan) {
		this.xiaodan = xiaodan;
	}
	public Integer getChaoshi() {
		return chaoshi;
	}
	public void setChaoshi(Integer chaoshi) {
		this.chaoshi = chaoshi;
	}
	public Integer getDadan() {
		return dadan;
	}
	public void setDadan(Integer dadan) {
		this.dadan = dadan;
	}
	public Integer getDadanchaoshi() {
		return dadanchaoshi;
	}
	public void setDadanchaoshi(Integer dadanchaoshi) {
		this.dadanchaoshi = dadanchaoshi;
	}
	
	
}
