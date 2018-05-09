<%@page import="com.clint.pinchewang.util.PincheUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link rel="stylesheet" type="text/css" href="pin_che_wang/getPincheList/pincheList.css?a=<%=Math.random()%>">

	<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="public/js/jquery.form.js"></script>
	<script type="text/javascript" src="pin_che_wang/getPincheList/getPincheXinxi.js?a=<%=Math.random()%>"></script>
	<script type="text/javascript" src="public/vConsole-2.2.0/dist/vconsole.min.js"></script>

  </head>
  
  <body>
  	<div class="zhazhaoceng"></div>
  
  	<div class="tiaojian">
  		<form id="f1" action="">
  		
  			条件区域
  			<input type="text" class="guanjianzi" name="guanjianzi" placeholder="多个关键字请用空格隔开">
  		
	  		<input type="button" onclick="xiugaichaxun();" value="搜索">
	  		
  		</form>
  	</div>
  	<div class="content">
  	</div>
  	
  	<!-- 翻页底部提示 -->
  	<div class="jieweitishi">
  		<img src='pin_che_wang/getPincheList/img/lodding.gif' class='fanyezhengzaichaxun'>
  		<div class="jieguowenzitishi"></div>
  	</div>
  	
  	
  	<!-- 查询正文提示 -->
  	<div class='chaxunjiazaikuang'>
		<img src='pin_che_wang/getPincheList/img/lodding.gif' class='zhengzaichaxun'>
		<div class='chaxunzhong'>查询中...</div>
	</div>
		
		
  </body>
  
  <script type="text/javascript">
  	//每页显示多少条
  	var pagesize = <%=PincheUtil.pagesize%>;
  	
  </script>
</html>
