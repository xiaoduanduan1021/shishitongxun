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

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	function fenxiang() {
		alert("执行分享方法");

		wx.onMenuShareAppMessage({
			title : '1', // 分享标题
			desc : '1', // 分享描述
			link : '1', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : '1', // 分享图标
			type : '1', // 分享类型,music、video或link，不填默认为link
			dataUrl : '1', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				// 用户确认分享后执行的回调函数
				alert("确认分享");
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
				alert("取消分享");
			}
		});
	}
</script>
</head>

<body>
	<a href="javascript:fenxiang();"> 111111111111111 </a>






	<script type="text/javascript">
		function copyUrl2() {
			var Url2 = document.getElementById("biao1");
			Url2.select(); // 选择对象
			document.execCommand("Copy"); // 执行浏览器复制命令
			alert("已复制好，可贴粘。");
		}
	</script>
	<textarea cols="20" rows="10" id="biao1">用户定义的代码区域</textarea>
	<input type="button" onClick="copyUrl2()" value="点击复制代码" />


















</body>
</html>
