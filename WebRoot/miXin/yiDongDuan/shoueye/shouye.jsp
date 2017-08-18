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

<title>密信</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" type="text/css" href="miXin/yiDongDuan/shoueye/shouye.css">
<link rel="stylesheet" type="text/css" href="miXin/yiDongDuan/public/public.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name = "format-detection" content = "telephone=no">


</head>

<body>
	<div class="mixintop">密信</div>
	<div class="anniu" onclick="location.href='miXin/yiDongDuan/faXinYeMian/faXinYeMian.jsp'">制作密信</div>
	<div class="jieshao">
		密信是一个网页加密工具，你可以把需要保密发送的内容通过密信发给你的朋友们，观看之后就会被销毁，不能再次转发和查看。
	</div>
	<div class="lianxiwomen">联系我们：手机15210647606，邮箱1476073219@qq.com，来信请注明密信反馈，恭候您的信息。</div>
</body>
</html>