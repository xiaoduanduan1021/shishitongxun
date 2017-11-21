<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Searche.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="public/js/jquery-1.10.2.min.js"></script>
  </head>
  
  <body>
		输入字符自动搜索从Lucene搜索引擎中	
		<input type="text" id="text" >
		<div id="neirong">
		</div>
  </body>
  <script type="text/javascript">
  
	  $(document).ready(function(){
	      $('input').bind('input propertychange', function() {
//	          console.log("搜索");
	          Searche();
	      });
	  });
	  
	  function Searche(){
		  
		  $.ajax({
		        url:"lucene/getSearch.action",
		        type:"post",
		        async:false,//true异步，false同步，默认异步
		        dataType:"json",
		        data:{
		            "text":$("#text").val()
		        },
		        success:function (data){
//		        	console.log(data);
		        	$("#neirong").text("");
		        	var listsearche = data.listsearche;
		        	for(var hang in listsearche){
		        		$("#neirong").append(listsearche[hang]+"</br>");
		        	}
		        },
		        error:function (){
		            //显示获取作业失败，或者网络断开
		        }
		    })
	  }
  
  
  </script>
</html>
