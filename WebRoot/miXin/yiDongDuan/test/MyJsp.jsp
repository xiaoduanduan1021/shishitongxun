<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="public/js/jquery-1.10.2.min.js"></script>
    <script src="public/layer/layer.js"></script>
    
    <style type="text/css">
    	.tishiceng{
    		background: white;
    		border: 1px solid #D8D8D8;
    	}
    </style>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
  
  <script type="text/javascript">
  
//提示层
	layer.msg('复制完成,去发送吧。',{
		  skin:'tishiceng',
		  time:5*1000
	});
	
	</script>
</html>
