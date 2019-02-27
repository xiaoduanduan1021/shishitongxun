<%@page import="com.clint.yinyue_xiazai.util.yinyueUtil"%>
<%@page import="com.clint.yinyue_xiazai.model.YinyueXiazai"%>
<%@page import="util.page.PageList"%>
<%@page import="com.clint.yinyue_xiazai.util.yinyueUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String basePath = yinyueUtil.getYuming(request);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <base href="<%=basePath%>">

<title>250音乐网  全部音乐列表 mp3免费下载 原版 高清 歌曲免费下载音乐网</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="250音乐网  全部音乐列表 mp3免费下载高清">
<meta http-equiv="description" content="250音乐网  全部音乐列表 mp3免费下载高清">



	<link rel="stylesheet" type="text/css" href="/yinyue_xiazai/yinyueList/fenye.css?a=1111">
												
												
	<link rel="stylesheet" type="text/css" href="/yinyue_xiazai/yinyueList/yinyuelist.css?a=1111">
												

<jsp:include page="/yinyue_xiazai/public/head/head.jsp"></jsp:include>

<script src="public/js/jquery-3.3.1.min.js"></script>

</head>

<body>
	
	<jsp:include page="/yinyue_xiazai/public/bodyTop/bodyTop.jsp"></jsp:include>


<%	  		PageList page1 = (PageList)request.getAttribute("page");  %>


<!-- 展开页码 -->
	  	<%=page1.getPageText() %>
<!-- 展开页码 -->

	  	
	  	<table  border="1" class='yinyueliebiao' cellspacing="0" style="border-collapse:collapse">
	  	
	  	<%

	  		List<YinyueXiazai> list = page1.getDatalist();
	  		for(int i=0;i<list.size();i++){
	  			YinyueXiazai yinyueXiazai = list.get(i);
	  			%>
			<tr>
				<td><%=yinyueXiazai.getId() %></td>
				  	
				<td>
				
					<a href="<%=basePath%>yinyue_xiazai/xiazai.action?id=<%=yinyueXiazai.getId() %>">
						<%=yinyueXiazai.getGequ_name() %>
					</a>
				
				</td>
				  	
			</tr>
	  			
	  			<%
	  		}
	  	%>
		</table>
	  	
	  	



<!-- 展开页码 -->
	  	<%=page1.getPageText() %>
<!-- 展开页码 -->
  	
  	
  	
  	
	<jsp:include page="/yinyue_xiazai/public/bodyBotom/bodyBotom.jsp"></jsp:include>


</body>
</html>
