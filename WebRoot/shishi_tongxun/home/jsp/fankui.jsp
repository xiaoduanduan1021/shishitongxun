<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>1010keke反馈</title>

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
		<h2>如果您遇到问题请反馈给我们哦！</h2>
		<p>如果您在了解过程中有什么疑问，或者在安装使用过程中遇到问题，请联系我们的客服，或者反馈到我们邮箱，客服会及时为您回复，我们的成长需要您的支持！谢谢！！</p>
		<ul class="thin">
			<li><b>QQ：</b>2439532880</li>
			<li><b>邮箱：</b>2439532880@qq.com</li>
		</ul>

		</section> 
		
		</section>
		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
		
	</div>
</body>

</html>