package com.clint.sysuser.model;


import java.io.Serializable;
//系统账户
public class SysUser implements Serializable {

	private static final long serialVersionUID = 8980868710582297727L;
	
	private Integer id;
	private String loginName;	//登录名
	private String passWord;	//密码
	private String telePhone;	//电话
	private String company;		//公司名
	private String email;		//用户注册时的邮箱
	private String creationTime; // 创建时间
	private Integer jihuo;//是否邮箱激活1激活0未激活
	private String jihuoMiyao;//激活秘钥
	
	
	public String getJihuoMiyao() {
		return jihuoMiyao;
	}
	public void setJihuoMiyao(String jihuoMiyao) {
		this.jihuoMiyao = jihuoMiyao;
	}
	public Integer getJihuo() {
		return jihuo;
	}
	public void setJihuo(Integer jihuo) {
		this.jihuo = jihuo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
			
}
