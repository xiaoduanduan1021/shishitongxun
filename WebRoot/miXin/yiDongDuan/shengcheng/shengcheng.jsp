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

<title>生成</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name = "format-detection" content = "telephone=no">

<link rel="stylesheet" type="text/css" href="miXin/yiDongDuan/shengcheng/shengcheng.css">
</head>

<body>

	<textarea class="dizhi" id="url"><%=basePath %>getMiXin.action?uuid=<%=request.getParameter("uuid")%></textarea>
	
	
	<div class="dianjifuzhi" onClick="copyUrl()">点击复制</div>
	
	<div class="shuoming">
		复制上方网址发送到qq或微信中,也可发送到：qq空间、朋友圈、论坛、贴吧等
	</div>
	
	<div class="zaixieyige" onclick="location.href='miXin/yiDongDuan/faXinYeMian/faXinYeMian.jsp'">再写一个</div>
	
</body>

	<script type="text/javascript">
		function copyUrl() {
			var Url = document.getElementById("url");
			Url.select(); // 选择对象
			document.execCommand("Copy"); // 执行浏览器复制命令
			alert("已复制好，去贴粘发送吧。");
		}
	</script>
	
</html>