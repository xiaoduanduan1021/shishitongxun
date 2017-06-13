<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>捐助</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.juanzhutu{
			width: 300px;
		}
	</style>
</head>

<body>
	<div id="container">
		<%@ include file="/shishi_tongxun/home/public/topBootom/top.jsp"%>
		<section id="content_main"> <section>
		
		<br>
		<br>
		<br>
		<br>
			<ul class="thin">
				<div>感谢您对1010keke的支持！</div>
				<p>微信打赏</p>
				<div><img class="juanzhutu" src="shishi_tongxun/home/img/juanzhuerweima.jpg"></div>
			</ul>
		<br>
		<br>


		</section> 
		
		
		</section>
		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
	</div>
</body>

</html>