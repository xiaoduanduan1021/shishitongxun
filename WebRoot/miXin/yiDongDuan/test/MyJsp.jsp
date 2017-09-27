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

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="public/js/jquery-1.10.2.min.js"></script>

</head>

<body>

	<script type="text/javascript">
		$.ajax({
			url : "getMiXin.action",
			type : "post",
			async : true,//true异步，false同步，默认异步
			dataType : "json",
			data : {
//				"openid" : openid
			},
			success : function(data) {
			},
			error : function() {
			}
		})
	</script>
</body>
</html>
