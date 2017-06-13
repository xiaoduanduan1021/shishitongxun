<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>聊天记录管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="shishi_tongxun/guanLiDuan/xinxiGuanli/liaoTianJiluGuanli/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
<script type="text/javascript" src="public/layer/layer.js"></script>

<style>
.textbox{
	height:20px;
	margin:0;
	padding:0 2px;
	box-sizing:content-box;
}
</style>
</head>

<body>
	<table id="box"></table>
	
	<div id="tb" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		查询内容：<input type="text" class="textbox" name="xinxi" style="width:110px">
		发送时间从：<input type="text" name="startdate" class="easyui-datebox" style="width:110px">
		到：<input type="text" name="enddate" class="easyui-datebox" style="width:110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
	</div>
</div>

</body>
</html>
