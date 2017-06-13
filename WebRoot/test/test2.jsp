<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<script type='text/javascript' src="public/js/jquery-1.10.2.min.js"></script>
		
		
	<link rel="stylesheet" href="public/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="public/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="public/kindeditor/kindeditor-all-min.js"></script>
	<!-- <script charset="utf-8" src="public/kindeditor/kindeditor-all.js"></script> -->
	<script charset="utf-8" src="public/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="public/kindeditor/plugins/code/prettify.js"></script>
	
	
	<script>
	var editor;	
		KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content1"]', {
				cssPath : 'public/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>public/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>public/kindeditor/jsp/file_manager_json.jsp',//指定浏览远程图片的服务器端程序。
				allowFileManager : true,//true时显示浏览远程服务器按钮。
				allowImageUpload : true,//true时显示图片上传按钮
				afterCreate : function() {//设置编辑器创建后执行的回调函数。
					var self = this;
					K.ctrl(document, 13, function() {//按键ctrl加回车提交
						self.sync();
						tijiao();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						tijiao();
					});
				},

				//工具栏
				items:[
				        'emoticons','source', '|', 'undo', 'redo', '|', 'clearhtml', 'selectall', '|',
				        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 
				        '|','baidumap'
				],
				afterChange : function(){
					onchange();
				},
			});
		});
		
	</script>
</head>

<body>
<div id="yulan"></div>
	<form name="example" method="post" action="test/test2.jsp">
		<textarea name="content1" id="kind" onchange="onchange();" style="width: 100%;"></textarea>
		<input type="button" name="button" onclick="tijiao();" value="提交" />
	</form>
</body>
<script type="text/javascript">
	function tijiao(){
		var html = editor.html();
		$("#yulan").html(html);
		//设置所有图片宽度最大100px
		$("#yulan img").css("max-width","100px");
	}
	var changehtml = 0;
	function onchange(){
		if(editor == null || changehtml == 1){
			return;
		}
		
		var html = editor.html();
		$("#yulan").html(html);
		
		//判断是否有图片
		if($("#yulan img").length ==0){
			return;
		}
		
		//设置所有图片宽度最大100px
		$("#yulan img").css("max-width","100px");
		console.log($("#yulan").html());
		//关闭修改事件
		changehtml = 1;
		editor.html($("#yulan").html());
		changehtml = 0;
		//发送
	}
</script>
</html>
