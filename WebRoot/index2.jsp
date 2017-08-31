<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>时刻通讯1010keke.com官方免费在线客服系统</title>
<link rel="stylesheet" type="text/css" href="cssJs/index.css">

<!-- 百度验证 -->
<meta name="baidu-site-verification" content="58TSMQWq3t" />
<!-- 百度验证 -->


</head>
<body>
	<div id="container">
		<%@ include file="/shishi_tongxun/home/public/topBootom/top.jsp"%>

		<section id="content_main"> 
		
		<article>
		<h2>“时刻通讯”完全免费的在线客服系统</h2>
		<p class="jianjie">1010keke是一个<span>完全免费</span>的在线客服系统，1010keke可以让您在网站上<span>迅速建立</span>一个跨平台的支持实时咨询交流的客服系统，
		不在因为网站没有客服系统而烦恼，不再因为顾客想联系您却联系不到而烦恼，解决顾客因为想资讯而又不想打电话的问题，
		点击即可联系，迅速简便，告别因实时通讯技术复杂而放弃，并且<span>1010keke还支持移动端</span>，用您的手机或者ipad平板电脑，
		在浏览器中打开1010keke客服端登陆后，即可时刻保持与顾客的咨询沟通
		是时候用他了1010keke时时刻刻为您通讯。</p>
		</article>
		
		 <article>
		<h2>客服端实例</h2>
		<img title="免费在线客服系统聊天系统时时刻刻www.1010keke.com客服端实例" alt="免费在线客服系统聊天系统时时刻刻www.1010keke.com客服端实例" class="shili" width="300" src="img/shilikefu.jpg"> </article>
		<article>
		<h2>顾客端实例</h2>
		<img title="免费在线客服系统聊天系统时时刻刻www.1010keke.com顾客端实例" alt="免费在线客服系统聊天系统时时刻刻www.1010keke.com顾客端实例" class="shili" width="300" src="img/shiliguke.jpg"> </article>

		
		
		
		
		
		
		<section>
		<h2>为什么选择“时刻通讯”</h2>


		<ul class="thin">
			<li><b>1完全免费：</b>完全免费在线客服系统</li>
			<li><b>2兼容所有终端：</b>兼容pc端移动端所有浏览器</li>
			<li><b>3简洁、快速、稳定、：</b>界面简洁，反应快速，信息发送秒传，运行稳定</li>
			<li><b>4持续更新：</b>功能持续更新扩展</li>
			<li><b>5兼容多平台：</b>java，php，html，htm，.net、、、</li>
			<li><b>6不限制注册数量：</b>不限制客服账户数量（坐席数量）</li>

		</ul>

		</section>
		
		
		
		
		 <section>
		<h2>平台和浏览器</h2>

		<img title="免费在线客服系统聊天系统时时刻刻www.1010keke.comFirefox, Chrome, Opera, Safari, IE6, IE7, IE8, IE9 beta全浏览器平台支持" alt="免费在线客服系统聊天系统时时刻刻www.1010keke.comFirefox, Chrome, Opera, Safari, IE6, IE7, IE8, IE9 beta全浏览器平台支持" src="img/browsers.jpg">
		<img title="免费在线客服系统聊天系统时时刻刻www.1010keke.com全IOS和Android移动平台支持" alt="免费在线客服系统聊天系统时时刻刻www.1010keke.com全IOS和Android移动平台支持" src="img/platforms.jpg">
		<ul class="thin">
			<li><strong>Windows:</strong> Chrome, Firefox, Internet
				Explorer, Safari, Opera</li>
			<li><strong>Windows (legacy):</strong> IE6, IE7, IE8, IE9, IE10,
				IE11</li>
			<li><strong>OSX:</strong> Safari, Firefox, Chrome, Opera</li>
			<li><strong>iOS:</strong> Mobile Safari: iPad, iPhone, iPod
				Touch</li>
			<li><strong>Android:</strong> Android 2.3 Browser</li>
			<li><strong>Blackberry:</strong> OS 7 Phone Browser, PlayBook
				Browser</li>
		</ul>
		</section> 

		<article>
		<h2>如何安装？</h2>
		<p>1010keke安装方法非常简单，点击页面上端菜单中的“安装”按钮进入参照步骤安装</p>
		</article>
	
		</section>
		
		<!-- <a href="javascript:jinrukefuduan();">点击这里登陆客服端</a> -->
		<%-- 	<% SysUser sysuser = (SysUser) request.getSession().getAttribute("sysUser");
		if(sysuser == null){
			request.getRequestDispatcher("SysUser/login.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("shishi_tongxun/xinxiFasong/kefu.jsp").forward(request, response);
		}
	%> --%>

<!-- 百度收录封面图 -->
<div id="tong" style="display: none;">
	<img src="img/baidushoulu.jpg">
</div>
<!-- 百度收录封面图 -->

		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
	</div>
</body>
</html>