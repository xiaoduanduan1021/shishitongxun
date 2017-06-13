<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>改密完成</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="SysUser/forgetPassWord/css/3updatePassWord.css">
  </head>
  
  <body>
<table class="tabledengluqu">
		  	<tr>
		  		<td class="tabletddengluqu">
		  		<!-- 流程模块 -->
		  		<style type="text/css">
					#liucheng1{
					    color: rgb(255,68,0);
					}
					#liucheng2{
					    color: rgb(255,68,0);
					}
					#liucheng3{
					    color: rgb(255,68,0);
					}
					#liucheng4{
					    color: rgb(255,68,0);
					}
				</style>
  		  		<div class="liuchengtishi" id="liucheng1">1.录入邮箱</div>
		  		<div class="liuchengtishi" id="liucheng2">2.选择账户</div>
		  		<div class="liuchengtishi" id="liucheng3">3.录入验证码新密码</div>
		  		<div class="liuchengtishi" id="liucheng4">4.改密完成</div>
		  		<!-- 流程模块 -->
		  		
		  		
		  		
				  <div class="denglumokuai">
					<form id="form1" action="SysUser/toEmailRand.action" method="post">
						<div class="zhanghaomimashuruqu">
							<div class="hang">
								<div class="gaimiwancheng">改密完成</div>
							</div>
						</div>
						<input type="button" value="去登陆" class="dengluOrZhuce" onclick="location.href='SysUser/login.jsp'">
					</form>
				  </div>
		  		</td>
		  	</tr>
		  </table>
  </body>
</html>
