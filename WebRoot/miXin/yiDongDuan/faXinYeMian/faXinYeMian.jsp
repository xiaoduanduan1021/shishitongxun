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

<title>添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name = "format-detection" content = "telephone=no">

<link rel="stylesheet" type="text/css" href="miXin/yiDongDuan/faXinYeMian/faXinYeMian.css">
<script type="text/javascript" src="public/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="public/js/jquery.form.js"></script>
<script type="text/javascript" src="miXin/yiDongDuan/faXinYeMian/faXinYeMian.js"></script>
</head>

<body>
	<form id="form1" action="addMiXin.action">
		<textarea class="wenbenkuang" placeholder="点击此处输入文字" name="content"></textarea>
		<div class="wancenganniu" onclick="wancheng();">下一步</div>
	</form>
	
</body>
</html>