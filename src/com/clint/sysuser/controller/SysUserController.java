package com.clint.sysuser.controller;


import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.email.FaYouXiang;
import util.string.MD5;
import util.string.RandNumber;
import util.string.StringCode;

import com.clint.service.PersonService;
import com.clint.shishitongxun.websocketsession.WebSocketSession;
import com.clint.sysuser.model.SysUser;
import com.clint.sysuser.service.SysUserService;

@Controller
@RequestMapping(value = "/SysUser")

public class SysUserController {
	
	@Resource(name = "sysUserService")
	private SysUserService sysUserService;
	
	//临时使用登录，存入session
	@RequestMapping(value = "/sysUserLogin")
	public void sysUserLogin(HttpSession session,SysUser sysUser,HttpServletResponse response,String rand) throws IOException {
		
		//检查验证码是否正确
		if(StringUtils.isBlank(rand)){
			response.getWriter().write("randError");
			return;
		}
		String sessionRand = (String)session.getAttribute("rand");
		if(!sessionRand.equals(rand)){
			response.getWriter().write("randError");
			return;
		}
		//如果验证码正确则
		//随机验证码，防止重复登录
		session.setAttribute("rand", String.valueOf(Math.random()));
		
		
		
		//检查用户名密码不为空
		if(StringUtils.isBlank(sysUser.getLoginName())){
			response.getWriter().write("loginNameNull");
			return;
		}
		if(StringUtils.isBlank(sysUser.getPassWord())){
			response.getWriter().write("passWordNull");
			return;
		}
		//检查所有字段长度
		//用户名
		if(sysUser.getLoginName().length()>20){
			response.getWriter().write("loginNameOverLength");
			return;
		}
		//密码
		if(sysUser.getPassWord().length()>20){
			response.getWriter().write("passWordOverLength");
			return;
		}

		//检查用户是否存在
		SysUser dbu = this.sysUserService.getSysUserByLoginName(sysUser.getLoginName());
		if(dbu==null){
			response.getWriter().write("loginNameNo");//用户名不存在
			return;
			
		}
		//检测用户是否激活
		if(dbu.getJihuo()==0){
			response.getWriter().write("sysUserWeiJiHuo");//用户未激活
			return;
		}
		
		//检查密码是否相同
		if(!dbu.getPassWord().equals(new MD5().getMD5(sysUser.getPassWord()))){
			response.getWriter().write("passWordNo");//密码不正确
			return;
		}
		
		//将用户存入session
		session.setAttribute("sysUser", dbu);
		response.getWriter().write("ok");
		return;
	}
	//注册
	@RequestMapping(value = "/sysUserRegister")
	public void sysUserRegister(HttpSession session,SysUser sysUser,HttpServletResponse response,String passWord2,String rand,HttpServletRequest request) throws IOException {
		
		//检查验证码是否正确
		String sessionRand = (String)session.getAttribute("rand");
		if(StringUtils.isBlank(rand)){
			response.getWriter().write("randError");
			return;
		}
		if(!sessionRand.equals(rand)){
			response.getWriter().write("randError");
			return;
		}
		//如果验证码正确则
		//随机验证码，防止刷注册
		
		session.setAttribute("rand", String.valueOf(Math.random()));
		
		//检查用户名密码不为空
		if(StringUtils.isBlank(sysUser.getLoginName())){
			response.getWriter().write("loginNameNull");
			return;
		}
		if(StringUtils.isBlank(sysUser.getPassWord())){
			response.getWriter().write("passWordNull");
			return;
		}
		//检查所有字段长度
		//用户名
		if(sysUser.getLoginName().length()>20){
			response.getWriter().write("loginNameOverLength");
			return;
		}
		//密码
		if(sysUser.getPassWord().length()>20){
			response.getWriter().write("passWordOverLength");
			return;
		}
		//手机号
		if(sysUser.getTelePhone().length()>20){
			response.getWriter().write("telePhoneOverLength");
			return;
		}
		//email
		if(sysUser.getEmail().length()>50){
			response.getWriter().write("emailOverLength");
			return;
		}
		//公司
		if(sysUser.getCompany().length()>50){
			response.getWriter().write("companyOverLength");
			return;
		}
		
		//检查密码是否相同
		if(!sysUser.getPassWord().equals(passWord2)){
			response.getWriter().write("passWordDifferent");
			return;
		}
		//验证邮箱格式是否正确
		String regexEmail = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if(!sysUser.getEmail().matches(regexEmail)){//邮箱格式错误
			response.getWriter().write("emailNo");
			return;
		}
		//检查用户名是否重复
		SysUser dbu = this.sysUserService.getSysUserByLoginName(sysUser.getLoginName());
		if(dbu!=null){
			response.getWriter().write("loginNameAlreadyExists");//用户名已经存在
			return;
		}
		
		//密码加密
		MD5 md = new MD5();
		
		sysUser.setPassWord(md.getMD5(sysUser.getPassWord()));
		//增加创建时间
		sysUser.setCreationTime(StringCode.getDateTime());
		
		//保存用户
		sysUser.setJihuo(0);
		//生成激活秘钥
		sysUser.setJihuoMiyao(String.valueOf(Math.random()));
		SysUser sysUserDb = this.sysUserService.addSysUser(sysUser);
		
		//发送邮箱激活信息
		//获取项目路径地址，域名加项目名
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		String wspath = basePath.substring(5, basePath.length());
		wspath = "//www.1010keke.com/";
		String jihuoDizhi = "http:"+wspath+"SysUser/jihuo.action?id="+sysUserDb.getId()+"&jihuoMiyao="+sysUser.getJihuoMiyao();

		String content = "";
		content += "<p>";
		content += "客服系统激活信息,您的账户为:"+sysUser.getLoginName()+"<br/>";
		content += "</p>";
		content += "<p>";
		content += "访问这个链接激活";
		content += "</p>";
		content += "<p>";
		content += "<a href=\""+jihuoDizhi+"\" target=\"_blank\" title=\""+jihuoDizhi+"\">"+jihuoDizhi+"</a><br/>";
		content += "</p>";
	
		String jieguo = new FaYouXiang().youjianfasong(sysUser.getEmail(), "客服系统激活信息", content);
		if(!jieguo.equals("ok")){
			//删除保存的账户
			sysUserService.deleteSysUser(sysUserDb);
			response.getWriter().write("emailInvalid");//email地址发送失败
			return;
		}
		
		response.getWriter().write("ok");
		return;
	}
	
	
	
	//激活账户
	@RequestMapping(value = "/jihuo")
	public String jihuo(SysUser sysUser,HttpServletRequest request){
		//获取注册的账户
		SysUser sysUserDb = sysUserService.getSysUserByid(sysUser.getId());
		//判断秘钥
		if (!sysUser.getJihuoMiyao().equals(sysUserDb.getJihuoMiyao())) {
			//秘钥不相同
			request.setAttribute("message", "0");
			return"/SysUser/jihuo.jsp";
		}
		//保存激活
		sysUserDb.setJihuo(1);
		sysUserService.updateSysUser(sysUserDb);
		request.setAttribute("message", "1");
		return"/SysUser/jihuo.jsp";
	}
	
	
	//------------------------------------修改密码流程-----------------------
	//根据邮箱获取账户列表
	@RequestMapping(value = "/getSysusersByEmail")
	public String getSysusersByEmail(String email,HttpServletRequest request){
		List<SysUser> l = sysUserService.getSysUsersByEmail(email);
		request.setAttribute("l", l);
		return "/SysUser/forgetPassWord/2selectSysuser.jsp";
	}
	//根据账户发送验证码到邮箱
	@RequestMapping(value = "/toEmailRand")
	public void toEmailRand(Integer id,HttpServletRequest request,String rand,HttpSession session,HttpServletResponse response) throws IOException{
		
		//检查验证码是否正确
		if(StringUtils.isBlank(rand)){
			response.getWriter().write("randError");
			return;
		}
		String sessionRand = (String)session.getAttribute("rand");
		if(!sessionRand.equals(rand)){
			response.getWriter().write("randError");
			return;
		}
		//如果验证码正确则
		//随机验证码，防止重复提交
		session.setAttribute("rand", String.valueOf(Math.random()));
		
		//验证id
		if (id==null) {
			response.getWriter().write("sysUserNull");
			return;
		}
		
		SysUser sysdb = sysUserService.getSysUserByid(id);
		if (sysdb == null) {
			return;
		}
		
		//生成验证码存入session：updatePassWordRand
		String updatePassWordRand = new RandNumber().getRandNumber(6);
		//发送到邮箱
		String content = "";
		content += "<p>";
		content += "客服系统改密信息，您的账户正在修改密码，请勿发于他人";
		content += "</p>";
		content += "<p>";
		content += "验证码："+updatePassWordRand;
		content += "</p>";
	
		String jieguo = new FaYouXiang().youjianfasong(sysdb.getEmail(), "客服系统改密信息", content);
		if(jieguo.equals("Invalid Addresses")){
			//发送失败提示，账号所对应的邮箱不可用，请联系官方申诉
			response.getWriter().write("emailInvalid");//email地址发送失败
			return;
		}
		
		//发送成功
		
		//将账户放入session
		session.setAttribute("updatePassWordRand", updatePassWordRand);
		response.getWriter().write("ok");
		session.setAttribute("updatePasswordSysUser", sysdb);
		return;
	}
	//修改密码
	@RequestMapping(value = "/updatePassWord")
	public void updatePassWord(HttpServletRequest request,String rand,HttpSession session,HttpServletResponse response,String updatePassWordRand,String passWord,String passWord2) throws IOException{
		
		//检查验证码是否正确
		if(StringUtils.isBlank(rand)){
			response.getWriter().write("randError");
			return;
		}
		String sessionRand = (String)session.getAttribute("rand");
		if(!sessionRand.equals(rand)){
			response.getWriter().write("randError");
			return;
		}
		//如果验证码正确则
		//随机验证码，防止重复提交
		session.setAttribute("rand", String.valueOf(Math.random()));
		
		//从session获取邮箱验证码
		String sessionUpdatePassWordRand = (String) session.getAttribute("updatePassWordRand");
		//判断验证码是否为空如果是空则表示因长时间未输入，请回到第一步重新输入邮箱
		if (StringUtils.isBlank(sessionUpdatePassWordRand)) {
			response.getWriter().write("sessionUpdatePassWordRandNull");
			return;
		}
		//判断验证码是否正确session：updatePassWordRand
		if(!sessionUpdatePassWordRand.equals(updatePassWordRand)){
			//如果失败则返回提示
			response.getWriter().write("updatePassWordRandError");
			return;
		}
		if(StringUtils.isBlank(passWord)){
			response.getWriter().write("passWordNull");
			return;
		}
		if(passWord.length()>20){
			response.getWriter().write("passWordOverLength");
			return;
		}
		//检查密码是否相同
		if(!passWord.equals(passWord2)){
			response.getWriter().write("passWordDifferent");
			return;
		}
		//如果正确则从session获取账户修改密码保存
		SysUser sessionSysUser = (SysUser) session.getAttribute("updatePasswordSysUser");
		sessionSysUser.setPassWord(new MD5().getMD5(passWord));
		sysUserService.updateSysUser(sessionSysUser);
		//返回完成
		response.getWriter().write("ok");
		return;
	}
	
}