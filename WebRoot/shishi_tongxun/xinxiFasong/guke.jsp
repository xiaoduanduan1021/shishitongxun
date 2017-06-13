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

	<title>My</title>
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
	<!-- <script type="text/javascript" src="public/vConsole-2.2.0/dist/vconsole.min.js"></script> -->
	<!-- 移动端日志输出工具 -->
	
	<link rel="stylesheet" type="text/css" href="public/loading/loading.css?a=<%=new Date().toGMTString()%>">
	<script type="text/javascript" src="public/loading/loading.js?a=<%=new Date().toGMTString()%>"></script>

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
		
		<div class="liaotianqu" id="liaotianqu" >
			<div class="gukemokuai">
				<div class="xinxiqu" id="xinxiqu"></div>
				<div class="fabuqu">
					<div class="bianjiqikuang"><textarea style="width: 100%;height: 80%;" id="xiaoxishurukuang"></textarea></div>
					<input type="button" id="shangchuantupian" onclick="shangchuantupian();" value="上传图片"/>
					<input type="button" onclick="faxiaoxi();" id="fabuanniu" value="发送">
				</div>
			</div>
		</div>
	
	<div id="yulan" style="display: none;"></div>
	
	
	<div style="display: none;">
		<!-- 上传图片组件kindeditor -->
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
		var kefuid = '<%=request.getParameter("userid")%>';
		//项目域名加路径
		var wspath = '<%=wspath%>';
		//新的httpSessionId
		var newHttpSessionId = '<%=session.getId()%>';
		
		var basepath = '<%=basePath%>';
		
		var accessSessionId = '<%=request.getParameter("accessSessionId")%>';
		
	</script>
	
<!-- 	<script type='text/javascript' src="public/js/jquery-1.10.2.min.js"></script> -->
	<script type='text/javascript' src="public/js/jquery-1.8.3.js"></script>
	<script type='text/javascript' src="public/js/jquery.form.js"></script>
	<script type='text/javascript' src='public/layer/layer.js'></script>
	<!-- 编辑器 -->
	<link rel="stylesheet" href="public/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="public/kindeditor/plugins/code/prettify.css" />
	<link rel="stylesheet" href="public/kindeditor/themes/example1/example1.css" />
	<script charset="utf-8" src="public/kindeditor/kindeditor-all.js?a=<%=Math.random()%>"></script>
	<script charset="utf-8" src="public/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="public/kindeditor/plugins/code/prettify.js"></script>
	<!-- 编辑器 -->
	
	<!-- 模拟websocket -->
	<!-- <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script> -->  
	<!-- 模拟websocket -->
	
	<link rel="stylesheet" type="text/css" href="shishi_tongxun/xinxiFasong/css/guke.css?a=<%=new Date().toGMTString()%>">
	<script type='text/javascript' src='shishi_tongxun/xinxiFasong/js/guke.js?a=<%=new Date().toGMTString()%>'></script>
	
</html>
