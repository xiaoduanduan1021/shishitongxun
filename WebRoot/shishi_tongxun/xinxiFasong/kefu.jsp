<%@page import="com.clint.sysuser.model.SysUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wspath = basePath.substring(5, basePath.length());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>客服端</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- 移动端日志输出工具 -->
<!-- 	<script type="text/javascript" src="public/vConsole-2.2.0/dist/vconsole.min.js"></script> -->
	<!-- 移动端日志输出工具 -->
	
	
	<link rel="stylesheet" type="text/css" href="public/loading/loading.css">
	<script type="text/javascript" src="public/loading/loading.js"></script>
	

</head>

<body>

<!-- 访问量统计 -->
			<%@ include file="/shishi_tongxun/home/public/jsp/cnzz.jsp"%>
<!-- 访问量统计 -->

<audio src="shishi_tongxun/xinxiFasong/music/SentMessage.ogg" id="sedMessageAudio">
您的设备不支持信息铃声
</audio>
<audio src="shishi_tongxun/xinxiFasong/music/xinxiyin.mp3" id="tishiyin">
您的设备不支持信息铃声
</audio>
	<div class="dingbugoneng">
		<input type="button" onclick="zhankaiGukeXiangqing();" class="tuichudenglu" value="展开顾客详情"/>
		<input type="button" onclick="location.href='SysUser/login.jsp'" class="tuichudenglu" value="退出登录"/>
	</div>
	<div class="zuocegukeliebiao">
		
	</div>

	<div class="liaotianqu" id="liaotianqu">
	</div>

	<div id="yulan" style="display: none;"></div>
	<div style="display: none;">
	
		<input type="button" id="uploadButton" accept="image/*" value="上传图片" />
		<!-- 图片放大器跳转方法 -->
		<form id="fangdaqiForm" action="shishi_tongxun/xinxiFasong/fangDaQi.jsp" target= "_blank" method="post">
			<input type="hidden" id="fangdaqiSrc" value="" name="src">
		</form>
		
		<!-- 七牛图片上传方法 -->
		<form method="post" id="qiniuImgUploadForm" action="http://upload.qiniu.com/" enctype="multipart/form-data" >
		  <input name="key" id="qiniukey" type="hidden" value="">
		  <input name="token" type="hidden" id="qiniuToken" value="">
		  <input name="file" type="file" id="qiniuFileImageUpload" onchange="qiniuOnchange();" accept="image/*" />
		</form>
	</div>
</body>
	<script>
		//客服id
		var kefuid = '<%SysUser sysuser = (SysUser)request.getSession().getAttribute("sysUser");
							if(sysuser != null){
								out.print(sysuser.getId());
							}
						%>';
		//项目域名加路径
		var wspath = '<%=wspath%>';
		var basepath = '<%=basePath%>';
	</script>
<!-- 	<script type='text/javascript' src="public/js/jquery-1.10.2.min.js"></script> -->
<script type='text/javascript' src="public/js/jquery-1.8.3.js"></script>
	<script type='text/javascript' src="public/js/jquery.form.js"></script>
	<script type='text/javascript' src='public/layer/layer.js'></script>
	
	<!-- 编辑器 -->
	<link rel="stylesheet" href="public/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="public/kindeditor/plugins/code/prettify.css" />
	<link rel="stylesheet" href="public/kindeditor/themes/example1/example1.css" />
	<script charset="utf-8" src="public/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="public/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="public/kindeditor/plugins/code/prettify.js"></script>
	<!-- 编辑器 -->
	
	
	<script type='text/javascript' src='public/js/Map.js'></script>
	<link rel="stylesheet" type="text/css" href="shishi_tongxun/xinxiFasong/css/kefu.css?a=<%=new Date().toString()%>">
	<script type='text/javascript' src='shishi_tongxun/xinxiFasong/js/kefu.js?a=<%=new Date().toString()%>'></script>
</html>
