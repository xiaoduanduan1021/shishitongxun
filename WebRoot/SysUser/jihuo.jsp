<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>激活信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	.jihuoxinxi{
	text-align: center;
    margin-top: 111px;
        font-size: 21px;
    }

</style>
  </head>
  
  <body>

  	<div id="container">
		<%@ include file="/shishi_tongxun/home/public/topBootom/top.jsp"%>
		<section id="content_main">
		
		
			<div class="jihuoxinxi">
				<%String message =(String) request.getAttribute("message"); 
					if(message.equals("1")){
						out.println("激活完成请登录");
						%><a href="javascript:jinrukefuduan();">点击登录</a><%
					}else{
						out.println("激活失败");
					}
				%>
			</div>
	
			</section>
		<%@ include file="/shishi_tongxun/home/public/topBootom/bootom.jsp"%>
  </body>
</html>
