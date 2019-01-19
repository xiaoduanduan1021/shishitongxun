<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>音乐处理2</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="yinyue_xiazai/chuli/chuli.css?a=<%=Math.random()%>">
<script type="text/javascript" src="public/js/jquery-1.10.2.min.js"></script>
</head>

<body>

	<input type="button" onclick="huoqu_liebiao();" class="shuaxin" value="刷新">
	
		<table border="1" cellspacing="0" class="liebiao" style="border-collapse:collapse">
		</table>
		
		
	
	
	<script type="text/javascript">
	
		var zuishangmianid = 0;
		//提交试听地址
		function tijiao(){
			
		
			$.ajax({
		        url:"yinyue_xiazai/saveXiazai.action",
		        type:"post",
		        async:true,//true异步，false同步，默认异步
		        dataType:"json",
		        data:{
		            "xiazai_dizhi":$("#url").val(),
		            "id":zuishangmianid,
		        },
		        success:function (data){
		        	console.log("提交完成");
			        huoqu_liebiao();
		        },
		        error:function (){
		        	alert("提交失败，请联系管理员");
		        }
		    })
			$("#url").val("");
		}
		
		//获取已提交列表
		function huoqu_liebiao(){
			$.ajax({
		        url:"yinyue_xiazai/weichuliLiebiao.action",
		        type:"post",
		        async:false,//true异步，false同步，默认异步
		        dataType:"json",
		        success:function (data){
		        	console.log(data);
		        	var data = data.data;
		        	console.log(data);
		        	
		        	var html = "";
		        				
		        	for(var i in data){
		        		console.log(i);
		        		console.log(data[i]);
		        		var yinyue = data[i];
		        		
		        		html+="<tr class=\"biaotihang\">";
		        		html+="<td>";
		        		html+="<div>"+yinyue.shiting_url+"</div>";
		        		html+="<br>";
		        		html+="<div>"+yinyue.gequ_name+"</div>";
		        		html+="<br>";
		        		
		        		if(i==0){
			        		html+="<textarea class=\"shuruyu\" id=\"url\" ></textarea>";
			        		html+="<br>";
			        		html+="<input type=\"button\" onclick=\"tijiao();\" class=\"queren\" value=\"确认\">";
			        		zuishangmianid = yinyue.id;
		        		}
		        		
		        		
		        		html+="</td>";
		        		html+="</tr>";
		        		
		        	}
					
					$(".liebiao").html(html);
					
		        },
		        error:function (){
		        	alert("失败，请联系管理员");
		        }
		    })
		}
	</script>
</body>
</html>
