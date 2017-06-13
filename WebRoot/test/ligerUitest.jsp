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

<title>My JSP 'ligerUitest.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="public/jQuery LigerUI V1.3.3/Source/lib/ligerUI/skins/Aqua/css/ligerui-all.css">
<script type="text/javascript" src="public/jQuery LigerUI V1.3.3/Source/lib/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="public/jQuery LigerUI V1.3.3/Source/lib/ligerUI/js/ligerui.min.js"></script>

<script type="text/javascript">
	$(function() {
		$.ligerDialog.success('提示内容');
	});
</script>


</head>

<body>

</body>
</html>
