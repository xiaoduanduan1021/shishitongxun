package com.clint.pinchewang.model;

import java.io.Serializable;

//密信，存储用户发送的密信
public class PinCheXinXi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8352530821287275171L;
	/**
	 * 
	 */
	private Integer id;
	//来自群名称
	private String qunMingCheng;
	//发送者昵称
	private String faSongZheNiCheng;
	//群id
	private String qunId;
	//发送者qq号
	private String faSongZheQQ;
	//发送时间
	private String faSongShiJian;
	//发送内容
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQunMingCheng() {
		return qunMingCheng;
	}
	public void setQunMingCheng(String qunMingCheng) {
		this.qunMingCheng = qunMingCheng;
	}
	public String getFaSongZheNiCheng() {
		return faSongZheNiCheng;
	}
	public void setFaSongZheNiCheng(String faSongZheNiCheng) {
		this.faSongZheNiCheng = faSongZheNiCheng;
	}
	public String getQunId() {
		return qunId;
	}
	public void setQunId(String qunId) {
		this.qunId = qunId;
	}
	public String getFaSongZheQQ() {
		return faSongZheQQ;
	}
	public void setFaSongZheQQ(String faSongZheQQ) {
		this.faSongZheQQ = faSongZheQQ;
	}
	public String getFaSongShiJian() {
		return faSongShiJian;
	}
	public void setFaSongShiJian(String faSongShiJian) {
		this.faSongShiJian = faSongShiJian;
	}
	@Override
	public String toString() {
		return "PinCheXinXi [id=" + id + ", qunMingCheng=" + qunMingCheng + ", faSongZheNiCheng=" + faSongZheNiCheng + ", qunId=" + qunId + ", faSongZheQQ=" + faSongZheQQ + ", faSongShiJian="
				+ faSongShiJian + ", content=" + content + "]";
	}
	
}
