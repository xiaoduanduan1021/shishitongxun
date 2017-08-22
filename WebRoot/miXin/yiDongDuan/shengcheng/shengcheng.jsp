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

    <script src="public/clipboard/clipboard.min.js"></script>
</head>

<body>

	<textarea class="dizhi" id="bar"><%=basePath %>getMiXin.action?uuid=<%=request.getParameter("uuid")%></textarea>
	
	<!-- <div class="dianjifuzhi" data-clipboard-action="cut" data-clipboard-target="#bar">点击复制</div> -->
	
	<button class="dianjifuzhi" data-clipboard-action="cut" data-clipboard-target="#bar">点击复制</button>
	    
	<div class="shuoming">
		复制上方网址发送到qq或微信中,也可发送到：qq空间、朋友圈、论坛、贴吧等
	</div>
	
	<div class="zaixieyige" onclick="location.href='miXin/yiDongDuan/faXinYeMian/faXinYeMian.jsp'">再写一个</div>
	
</body>

	<script type="text/javascript">
	 	var clipboard = new Clipboard('.dianjifuzhi');

	    clipboard.on('success', function(e) {
	    	alert("复制成功。");
	    });

	    clipboard.on('error', function(e) {
	    	alert("复制失败了！手动复制吧");
	    });
	</script>
	
</html>