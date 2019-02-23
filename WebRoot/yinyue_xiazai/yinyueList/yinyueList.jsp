<%@page import="com.clint.yinyue_xiazai.model.YinyueXiazai"%>
<%@page import="util.page.PageList"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'yinyueList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%
  		PageList page1 = (PageList)request.getAttribute("page"); 
  		List<YinyueXiazai> list = page1.getDatalist();
  		for(int i=0;i<list.size();i++){
  			YinyueXiazai yinyueXiazai = list.get(i);
  			%>
			  	<%=yinyueXiazai.getId() %>
			  	<%=yinyueXiazai.getGequ_name() %>
  				<br>
  				<br>
  			
  			<%
  		}
  		
  		
  	%>
  	<%=page1.getPageText() %>
  	<%=page1.getPageOption() %>
  	
  </body>
</html>
