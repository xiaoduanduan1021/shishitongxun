package com.clint.shishitongxun.xinxi.model;

import java.io.Serializable;
//顾客类，存储每个连接的顾客，注册的时候存储，并且存储该顾客对应的客服，如果一台设备链接了多个客服，那么对于每个客服都会生成一个顾客对象
public class Guke implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5365310126706906715L;
	
	// id
	private Integer id;
	// httpsessionid（gukeId）
	private String httpSessionId;
	// 对应客服id
	private Integer kefuId;
	// 注册时间链接时间
	private String zhuceShijian;
	//备注，客服对顾客的备注
	private String beizhu;
	//是否有新消息
	private Integer newXinxi;//1表示有，0表示没有
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
	

	private String accessSessionId;//访问记录id
	
	
	public String getAccessSessionId() {
		return accessSessionId;
	}

	public void setAccessSessionId(String accessSessionId) {
		this.accessSessionId = accessSessionId;
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

	public Integer getNewXinxi() {
		return newXinxi;
	}

	public void setNewXinxi(Integer newXinxi) {
		this.newXinxi = newXinxi;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHttpSessionId() {
		return httpSessionId;
	}

	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}

	public Integer getKefuId() {
		return kefuId;
	}

	public void setKefuId(Integer kefuId) {
		this.kefuId = kefuId;
	}

	public String getZhuceShijian() {
		return zhuceShijian;
	}

	public void setZhuceShijian(String zhuceShijian) {
		this.zhuceShijian = zhuceShijian;
	}

}
