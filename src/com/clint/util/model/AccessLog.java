package com.clint.util.model;


import java.io.Serializable;
//访问日志
public class AccessLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -368040182079402254L;
	
	private Integer id;
	private String url;//访问地址
	private String startTime; //进入页面时间
	private String endTime;//离开页面时间
	private long residenceTime;//停留时间毫秒
	private long startTimeS;//进入时间毫秒
	private long endTimeS;//离开时间毫秒
	private String accessSessionId; //访问人
	private String sourceUrl;//来源页面地址
	private String title;//受访页面标题
	private String yemianid;//每个页面每次访问生成新的id;
	
	
	public String getYemianid() {
		return yemianid;
	}
	public void setYemianid(String yemianid) {
		this.yemianid = yemianid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public long getResidenceTime() {
		return residenceTime;
	}
	public void setResidenceTime(long residenceTime) {
		this.residenceTime = residenceTime;
	}
	public long getStartTimeS() {
		return startTimeS;
	}
	public void setStartTimeS(long startTimeS) {
		this.startTimeS = startTimeS;
	}
	public long getEndTimeS() {
		return endTimeS;
	}
	public void setEndTimeS(long endTimeS) {
		this.endTimeS = endTimeS;
	}
	public String getAccessSessionId() {
		return accessSessionId;
	}
	public void setAccessSessionId(String accessSessionId) {
		this.accessSessionId = accessSessionId;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
