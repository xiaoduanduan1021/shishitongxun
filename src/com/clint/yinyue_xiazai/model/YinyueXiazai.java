package com.clint.yinyue_xiazai.model;

import java.io.Serializable;

public class YinyueXiazai implements Serializable {

	private Integer id;
	private String shiting_url;//试听地址
	private String uuid;//用户id
	private String datetime;//创建时间
	private String gequ_name;//歌曲名称
	private Integer status;//状态：0或空未完成，1完成，2未分析出
	private String xiazai_dizhi;//下载地址
	
	
	
	public String getXiazai_dizhi() {
		return xiazai_dizhi;
	}
	public void setXiazai_dizhi(String xiazai_dizhi) {
		this.xiazai_dizhi = xiazai_dizhi;
	}
	public String getGequ_name() {
		return gequ_name;
	}
	public void setGequ_name(String gequ_name) {
		this.gequ_name = gequ_name;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShiting_url() {
		return shiting_url;
	}
	public void setShiting_url(String shiting_url) {
		this.shiting_url = shiting_url;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "YinyueXiazai [id=" + id + ", shiting_url=" + shiting_url + ", uuid=" + uuid + "]";
	}

	

}
