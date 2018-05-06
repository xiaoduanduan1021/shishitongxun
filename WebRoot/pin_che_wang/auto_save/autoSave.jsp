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

<title>自动录入</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="public/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="public/js/jquery.form.js"></script>
</head>

<body>
	<form id="form1" action="">
		名： <br> <br> <br> 内容： <br>
		<textarea rows="7" name="content" id="content" cols="40"></textarea>
		<br>
	</form>
	<input type="button" onclick="tijiao();" value="提交">
	<div id="jieguo">yes</div>
	<script type="text/javascript">
	function tijiao(){
		
		$("#jieguo").text("no");
		var options = {
			url : "autoSavePPXX.action",
			type : "POST",
			dataType : "json",
			success : function(msg) {
				console.log(msg);
				//清空输入框
				$("#content").val("");
				$("#jieguo").text("yes");
			},
			error : function() {
				console.log("error");
			}
		}
		$('#form1').ajaxSubmit(options);//form5是表单的id，ajaxSubmit方法是jQuery的ajax提交表单方法

	}
	</script>
</body>
</html>
