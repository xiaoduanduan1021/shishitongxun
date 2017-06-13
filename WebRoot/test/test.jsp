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

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!-- 加载编辑器的容器 -->
	<script id="container" name="content" type="text/plain">
    </script>
	<!-- 配置文件 -->
	<script type="text/javascript" src="public/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="public/ueditor/ueditor.all.js"></script>
	
	<input onclick="shangchuan();" type="button">
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('container',{
			autoClearinitialContent:true,
			customstyle:"width:50%;",
		}
		);
	</script>
</body>
</html>
