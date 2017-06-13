<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>体验</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="shishi_tongxun/home/public/js/open.js"></script>
</head>

<body>
	<div id="container">
		<%@ include file="/shishi_tongxun/home/public/topBootom/top.jsp"%>

		<section id="content_main"> <article>
		<h2>体验“1010keke”pc端(电脑端)</h2>
		<p>注意：如果体验的人比较多可能会被挤掉哦！还是注册好好体验比较好哦！</p>
		<p>
			<a href="javascript:jinrukefuduan();">点击体验客服端</a>
		</p>
		登陆账号test密码123
		<p>
			<a href="javascript:jinrugukeduan(3);">点击体验顾客端</a>
		</p>


		<table>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>
		</article> <article>
		<h2>体验“1010keke”移动端(手机或者平板电脑)</h2>
		<p>打开微信或者扫码工具扫码体验</p>
		<p>您可以在电脑上打开客服端，在手机上打开顾客端来互相发消息哦！</p>

		<table>
			<tr>
				<td>客服端</td>
				<td>顾客端</td>
			</tr>
			<tr>
				<td><img alt="1010keke.com时时刻刻通讯客服端二维码体验移动端手机端" src="shishi_tongxun/home/img/kefuerweima.png" name="1010keke.com时时刻刻通讯客服端二维码体验移动端手机端" title="客服端二维码体验移动端手机端"></td>
				<td><img alt="1010keke.com时时刻刻通讯顾客端二维码体验移动端手机端" src="shishi_tongxun/home/img/gukeerweima.png" name="1010keke.com时时刻刻通讯顾客端二维码体验移动端手机端" title="顾客端二维码体验移动端手机端"></td>
			</tr>
		</table>
		</article> <section>
		<h2>操作说明</h2>


		<ul class="thin">
			<li><b>注册安装：</b>请点击上端菜单中的安装按钮，参照安装流程</li>
			<li><b>进入顾客端：</b>页面显示链接客服完成</li>
			<li><b>发送消息：</b>下方输入框内输入想咨询的问题，点击右侧发送，即可发送信息，如果咨询的客服不在线， 则会提示“客服不在线已经留言”此留言在客服登录系统时会提示新信息</li>
			<li><b>进入客服端：</b>当顾客咨询时页面提示有新消息， 同时左侧出现一个方形模块，方形内显示一个数字（该数字绝对唯一，代表一个顾客咨询对话）</li>
			<li><b>查看接收消息：</b>点击左侧方形模块，右侧展开与该顾客的对话窗口，在窗口内显示对方信息（顾客左侧）和自己发送的信息（客服右侧）</li>
			<li><b>发送消息：</b>在下方输入内容，点击发送，即可回复顾客咨询，如果顾客关闭浏览器或者关闭页面则会提示“对方不在线已留言”，待下次顾客再打开您的客服 咨询页面，信息会自动显示在顾客页面上</li>

		</ul>

		</section> </section>


		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
	</div>
</body>
</html>
