<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>呵呵</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="miXin/yiDongDuan/faXinYeMian/faXinYeMian.css">
<script type="text/javascript" src="public/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="public/js/jquery.form.js"></script>
<script type="text/javascript" src="miXin/yiDongDuan/faXinYeMian/faXinYeMian.js"></script>
</head>

<body>
	<form id="form1" action="addMiXin.action">
		<textarea name="content"></textarea>
		<input type="button" onclick="wancheng();" value="完成" />
	</form>
</body>
</html>