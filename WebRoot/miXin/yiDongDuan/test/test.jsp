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
</head>

<body>

	<script type="text/javascript">
		function copyUrl2() {
			var Url2 = document.getElementById("biao1");
			Url2.select(); // 选择对象
			document.execCommand("Copy"); // 执行浏览器复制命令
			alert("已复制好，可贴粘。");
		}
		
		function xuanze() {
			var Url2 = document.getElementById("biao1");
			Url2.select(); // 选择对象
		}
		function fuzhi() {
			document.execCommand("Copy"); // 执行浏览器复制命令
			alert("已复制");
		}
	</script>
	<textarea cols="20" rows="10" id="biao1">用户定义的代码区域</textarea>
	<input type="button" onClick="copyUrl2()" value="点击复制代码" />
	<input type="button" onClick="xuanze()" value="点击选择" />
	<input type="button" onClick="fuzhi()" value="点击复制" />

















</body>
</html>
