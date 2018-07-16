package com.clint.xiamipinglun.mobel;

import java.io.Serializable;

//百度链接下载记录
public class BaiduLianjeiXiazaiJilu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 26286624690060412L;
	private Integer id;
	
	private String lianJieMing;//连接名
	private Integer chakanCishu;//查看次数
	private Integer baocunCishu;//保存次数
	private Integer xiazaiCishu;//下载次数
	private String jianceDate;//检测时间
	
	
	public String getJianceDate() {
		return jianceDate;
	}
	public void setJianceDate(String jianceDate) {
		this.jianceDate = jianceDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLianJieMing() {
		return lianJieMing;
	}
	public void setLianJieMing(String lianJieMing) {
		this.lianJieMing = lianJieMing;
	}
	public Integer getChakanCishu() {
		return chakanCishu;
	}
	public void setChakanCishu(Integer chakanCishu) {
		this.chakanCishu = chakanCishu;
	}
	public Integer getBaocunCishu() {
		return baocunCishu;
	}
	public void setBaocunCishu(Integer baocunCishu) {
		this.baocunCishu = baocunCishu;
	}
	public Integer getXiazaiCishu() {
		return xiazaiCishu;
	}
	public void setXiazaiCishu(Integer xiazaiCishu) {
		this.xiazaiCishu = xiazaiCishu;
	}
	
	
}
