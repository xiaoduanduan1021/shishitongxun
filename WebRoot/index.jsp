<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>250xyz.xyz</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<style type="text/css">

.anniu{
    font-size: 34px;
    }
</style>
</head>

<body>

	<%
		//	request.getRequestDispatcher("miXin/yiDongDuan/shoueye/shouye.jsp").forward(request, response);
		//	response.sendRedirect("miXin/yiDongDuan/shoueye/shouye.jsp");
	%>

	<a class="anniu" href="yinyue_xiazai/yinyue_xiazai_home.action">
		音乐下载
	</a>
	
	<br>
	<br>
	
	<a class="anniu" href="yewujieshao">
		网站开发业务介绍
	</a>

</body>
</html>
