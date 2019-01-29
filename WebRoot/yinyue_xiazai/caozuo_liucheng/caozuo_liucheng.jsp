<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>音乐下载操作流程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="yinyue_xiazai/caozuo_liucheng/caozuoliucheng.css?a=<%=Math.random()%>">

  </head>
  
  <body>
  	<div class="juzhong">
  		<div class="biaoti">如何下载酷狗收费音乐</div>
  		
	  	<div class="wenzi">
	  		<span class="xuhao">1</span>
	  		点击这个进入酷狗网站,如下图
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/1.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">2</span>
	  		然后搜索你要找的歌曲，或者手动查找到想要的歌曲
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/2.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">3</span>
	  		然后点击进入播放页面
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/3.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">4</span>
	  		然后复制浏览器地址
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/4.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">5</span>
	  		然后回到我的网站粘贴在输入框，箭头指的方向
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/5.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">6</span>
	  		然后点击提交
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/6.png">
	  	
	  	<div class="wenzi">
	  		<span class="xuhao">7</span>
	  		稍等片刻，然后下面就会出现下载地址，点击下载按钮下载，或者复制链接去迅雷下载，或者在浏览器下载
	  	</div>
	  	<img alt="" class="tupian" src="yinyue_xiazai/caozuo_liucheng/img/7.png">
  	</div>
  	
  </body>
</html>
