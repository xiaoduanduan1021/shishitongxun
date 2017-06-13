<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>录入新密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="public/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="public/js/jquery.form.js"></script>
	<link rel="stylesheet" type="text/css" href="SysUser/forgetPassWord/css/3updatePassWord.css">
	<script type="text/javascript" src="public/layer/layer.js"></script>
	
	
	<script type="text/javascript">
		//输入邮箱验证码
		//输入新密码
		//输入页面验证码
		//提交
		//提示验证码错误
		//邮箱验证码错误
		//由于长时间未输入验证码，请回到第一步重新输入邮箱
		//提示改密完成
	</script>
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
								<div class="shuruming">邮箱验证码：</div>
								<div class="shuruzhi"><input name="updatePassWordRand" id="updatePassWordRand" type="password"></div>
							</div>
							<div class="hang">
								<div class="shuruming">密码：</div>
								<div class="shuruzhi"><input name="passWord" id="passWord" type="password"></div>
							</div>
							<div class="wenzitishi" id="mimatishi">密码长度20个字符以下</div>
							<div class="hang">
								<div class="shuruming">重复密码：</div>
								<div class="shuruzhi"><input name="passWord2" id="passWord2" type="password"></div>
							</div>
							
							<div class="hang">
								<div class="shuruming">验证码：</div>
								<div class="yanzhengmashuru"><input id="rand" name="rand" type="text"/></div>
								<div class="yanzhengma"><img id="randImage" src="public/jsp/image.jsp" onclick="loadimage()"></div>
							</div>
							
						</div>
						<input type="button" value="确认" class="dengluOrZhuce" onclick="tijiao();">
					</form>
				  </div>
		  		</td>
		  	</tr>
		  </table>
  </body>
  <script type="text/javascript">
		function tijiao(){
			
			var options={
					url:"SysUser/updatePassWord.action",
					type:"POST",
					dataType:"text",
					success:function(msg){
						if(msg=="ok"){
							location.href="SysUser/forgetPassWord/4updatePassWordOk.jsp";
						}else{
							//刷新验证码
							loadimage();
						}
						if(msg=="randError"){
							layer.alert("验证码不正确");
						}
						if(msg=="sessionUpdatePassWordRandNull"){
							layer.alert("由于长时间未输入验证码，<br>请回到第一部重新录入邮箱");
						}
						if(msg=="updatePassWordRandError"){
							layer.alert("邮箱验证码错误");
						}
						if(msg=="passWordNull"){
							layer.alert("请输入密码");
						}
						if(msg=="passWordOverLength"){
							layer.alert("密码长度不能超过20个字符");
						}
						if(msg=="passWordDifferent"){
							layer.alert("两次密码不同");
						}

					},
					error:function(){
						layer.alert("登录失败");
						//刷新验证码
						loadimage();
					}
			}
			$('#form1').ajaxSubmit(options);
		}
		
		function loadimage(){
			document.getElementById("randImage").src= "<%=path%>/public/jsp/image.jsp?"+Math.random(); 
			//清空验证码输入框
			document.getElementById("rand").value="";
		}
	</script>
</html>
