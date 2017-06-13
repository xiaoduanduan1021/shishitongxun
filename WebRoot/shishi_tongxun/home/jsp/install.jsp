<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>安装</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<div id="container">
		<%@ include file="/shishi_tongxun/home/public/topBootom/top.jsp"%>
		<section id="content_main"> <section>
		<h2>安装过程非常简单</h2>
		<ul class="thin">
			<li><b>1注册：</b>注册方法，点击这里<a target="_blank" href="SysUser/register.jsp">进入注册页面</a> 在注册页面输入账户、密码、邮箱，其他内容可义不输入 点击提交进入邮箱点击激活链接，完成激活</li>
			<li><b>2登录：</b><a target="_blank" href="SysUser/login.jsp">点击这里登录</a>在页面输入账户密码登录</li>
			<li><b>3安装：</b>登录完成后回到安装页面 此时安装页面下方已经列出所有客服按钮样式， 选择其中一个复制样式右侧的源码，放在您网站的html页面（您要增加客服按钮的页面，也可以是jsp页面）的指定区域， 不同的样式代码放在不同的区域 如果您了解web开发技术，则可以复制访问地址， 放在您的代码中进行自定义样式跳转 复制完成代码后，保存您的页面，启动您的服务器，此时在进入您的页面在右侧（或者您指定的区域）将看到客服咨询按钮，点击按钮进入顾客端</li>
			<li><b>4测试使用：</b>进入1010keke官网，点击上端注册预登陆，登录您的账户，则会进入您的客服端等待客服的咨询， 在您的网站页面点击客服咨询按钮， 进入在线客服的顾客端（咨询窗口），此时即可向客服发送消息 ，请互相发送消息测试，消息发送和接收会播放铃声，</li>
			<li><b>5多客服（多坐席）：</b>如果您想注册多个客服，再次进入注册页面注册登录即可，回到安装页面复制源码粘贴， 与上述操作步骤相同，您即可获得更多客服坐席 我站不限制客服注册数量，同一个邮箱可以注册多个客服账户</li>
		</ul>

		</section> 
		
		
		
		
		
		
		<section>
		<h2>我的源码</h2>
		<% 
		if(sysuser == null){
			%>您还没有登录！请先登录再来复制源码,登陆之后回到此页面刷新即可看到源码。<%
		}else{
			%>
		
		<table class="yuanmabiao" border="1" cellspacing="0" style="border-collapse:collapse">
			<tr>
				<td width="10%">说明</td>
				<td width="10%">样式</td>
				<td>源码</td>
				<td width="10%">粘贴位置</td>
			</tr>
			<tr>

				<td>在页面右侧悬浮，页面上下滑动时保持在右侧中间悬浮，点击打开客服咨询。</td>

				<td><img onclick="jinrugukeduan(1);" width="110" src="shishi_tongxun/home/img/kefuanniu1.jpg"></td>

				<td><textarea class="yuanmakuang"><!-- 客服源码 -->
<script type="text/javascript">var sysuserid=<%=sysuser.getId() %>;</script>
<script type="text/javascript" src="<%=basePath %>public/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>shishi_tongxun/fangwenJilu/jiLu.js"></script>
<script type="text/javascript" >var base = '<%=basePath%>';</script>
<script type="text/javascript" src="<%=basePath %>shishi_tongxun/home/public/js/open.js"></script>
<div style=" position: fixed; right: 11px; top: 45%;cursor:hand;">
	<img onclick="jinrugukeduan(<%=sysuser.getId() %>);" width="110" src="<%=basePath %>shishi_tongxun/home/img/kefuanniu1.jpg">
</div>
<!-- 客服源码 --></textarea></td>

				<td><div style="float: left;">粘贴位置页面的body标签内</div></td>

			</tr>
		</table>
			<%
		}
		%>
		
		
		</section> 
		
		
		
		
		
		
		
		<section>
		<h2>如果安装过程遇到问题怎么办？</h2>
		<ul class="thin">
			<li><b>1在线客服：</b>点击右侧在线客服联系1010keke客服解决</li>
			<li><b>2联系客服QQ：</b>qq：2439532880</li>
			<li><b>3邮箱：</b>2439532880@qq.com</li>
		</ul>
		</section>
		
		</section>
		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
	</div>
</body>

</html>