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

<title>音乐下载</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="yinyue_xiazai/home/home.css?a=<%=Math.random()%>">

</head>

<body>
	<div class="shuoming">
		
		
		<div class="wenzihang">本站可以免费下载所有网上的音乐，包括酷狗音乐，ik123深港dj等等,只要能在浏览器打开试听地址即可。</div>
		<div class="wenzihang">操作步骤：</div>
		<div class="wenzihang">1：在浏览器打开酷狗音乐网站，或者其他音乐网站</div>
		<div class="wenzihang">2：搜索或找到你要的音乐，点击进入试听播放页面</div>
		<div class="wenzihang">3：复制地址栏的地址，粘贴到下面输入框</div>
		<div class="wenzihang">4：点击确定</div>
		<div class="wenzihang">4：稍等片刻在下面列表就会显示下载地址</div>
		<div class="wenzihang">5：将下载地址复制到迅雷，或者浏览器直接访问，或者其他下载工具进行下载</div>
		
		<div class="shuruweizhi">
			
			<textarea class="shuruyu"></textarea>
				<br>
			<input type="button" class="queren" value="确认">
			
		</div>
			
	</div>
</body>
</html>
