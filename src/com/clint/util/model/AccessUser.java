package com.clint.util.model;



//访问的用户，该用户不是咨询的用户//访问页面时生成的，当点击客服连接时将关联这个用户的id和顾客id
public class AccessUser {
	private Integer id;
	private String accessSessionId;//访问人id
	
	
	//ip
	private String ip;
	//国家
	private String country;
	//国家id
	private String country_id;
	//地区
	private String area;
	//地区id
	private String area_id;
	//区域
	private String region;
	//区域id
	private String region_id;
	//城市
	private String city;
	//城市id
	private String city_id;
	//县
	private String county;
	//县id
	private String county_id;
	//Internet服务提供商;  
	private String isp;
	
	
	//创建时间
	private String createTime;
	//账户id
	private Integer sysuser;//对应账户
	
	//访客系统信息
	private String systemInformation;
	
	
	
	public Integer getSysuser() {
		return sysuser;
	}
	public void setSysuser(Integer sysuser) {
		this.sysuser = sysuser;
	}
	public String getSystemInformation() {
		return systemInformation;
	}
	public void setSystemInformation(String systemInformation) {
		this.systemInformation = systemInformation;
	}
	public String getAccessSessionId() {
		return accessSessionId;
	}
	public void setAccessSessionId(String accessSessionId) {
		this.accessSessionId = accessSessionId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCounty_id() {
		return county_id;
	}
	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
