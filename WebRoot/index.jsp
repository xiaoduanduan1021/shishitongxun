<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>250xyz.xyz</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<style type="text/css">

.anniu{
    font-size: 34px;
    }
</style>

<!-- 百度统计 -->
<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "https://hm.baidu.com/hm.js?c63ca1c34af49d647022ac5e60e130ac";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
</script>
<!-- 百度统计 -->




</head>

<body>

	<%
		//	request.getRequestDispatcher("miXin/yiDongDuan/shoueye/shouye.jsp").forward(request, response);
		//	response.sendRedirect("miXin/yiDongDuan/shoueye/shouye.jsp");
	%>

	<a class="anniu" href="yinyue_xiazai/yinyue_xiazai_home.action">
		酷狗，ik123，DJ音乐下载
	</a>
	
	<br>
	<br>
	
	<a class="anniu" href="yewujieshao">
		网站开发业务介绍
	</a>
	<br>
	<br>
	
	<a class="anniu" href="douyin/jihe4ban.html">
		抖音音乐批量下载永久更新
	</a>
	<br>
	<br>
	<a class="anniu" href="">
		酷狗DJ热歌前一百加微信
	</a>
	<br>
	<br>
	<a class="anniu" href="">
		酷狗TOP500排行榜加微信
	</a>
	<br>
	<br>
	<a class="anniu" >
		微信dyyy00001
	</a>

</body>
</html>
