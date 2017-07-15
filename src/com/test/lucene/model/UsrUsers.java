package com.test.lucene.model;



public class UsrUsers {

	private Integer id;
	private String openid;
	private String telephone;//联想定制
	private String names;//联想定制
	private String nickname;
	private Integer sex;
	private Integer subscribe;
	private String country;
	private String province;
	private String city;
	private String headimgurl;
	private String language;
	private String subscribe_time;
	private String creat_time;//关注时间
	private Integer statu;//0: 关注； 1: 取消； 2:新关注，未获绑定姓名的。
	private String fakeid;//微信中的用户id
	private String email;
	private Integer integral;
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFakeid() {
		return fakeid;
	}
	public void setFakeid(String fakeid) {
		this.fakeid = fakeid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribeTime) {
		subscribe_time = subscribeTime;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creatTime) {
		creat_time = creatTime;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	
}
