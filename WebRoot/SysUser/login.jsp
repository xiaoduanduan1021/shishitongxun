<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
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
	<link rel="stylesheet" type="text/css" href="SysUser/css/login.css">
<script type="text/javascript" src="public/layer/layer.js"></script>

	<script type="text/javascript">
		function loadimage(){
			document.getElementById("randImage").src= "<%=path%>/public/jsp/image.jsp?"+Math.random(); 
			//清空验证码输入框
			document.getElementById("rand").value="";
		}
	
		function tijiao(){
			//将账号存储到本地下次自动输入
			localStorage.setItem("loginname",$("#loginname").val());
			var options={
					url:"SysUser/sysUserLogin.action",
					type:"POST",
					dataType:"text",
					success:function(msg){
						if(msg=="ok"){
							layer.alert("登录完成");
							location.href="shishi_tongxun/xinxiFasong/kefu.jsp";
						}else{
							//刷新验证码
							loadimage();
						}
						if(msg=="randError"){
							layer.alert("验证码不正确");
						}
						if(msg=="loginNameNull"){
							layer.alert("用户名为空");
						}
						if(msg=="passWordNull"){
							layer.alert("密码为空");
						}
						if(msg=="loginNameOverLength"){
							layer.alert("用户名不能超过20个字符");
						}
						if(msg=="passWordOverLength"){
							layer.alert("密码不能超过20个字符");
						}
						if(msg=="loginNameNo"){
							layer.alert("用户不存在");
						}
						if(msg=="passWordNo"){
							layer.alert("密码不正确");
						}
						if(msg=="sysUserWeiJiHuo"){
							layer.alert("账户未激活，请到邮箱激活");
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
	</script>
  
  </head>
  
  <body>
  
  <!-- 访问量统计 -->
			<%@ include file="/shishi_tongxun/home/public/jsp/cnzz.jsp"%>
<!-- 访问量统计 -->


  <table class="tabledengluqu">
  	<tr>
  		<td class="tabletddengluqu">
  		
		  <div class="denglumokuai">
		  	<div class="dengluzhuceanniu">
		  		<div class="dengluanniu">快速登录</div>
		  		<div class="zhuceanniu" onclick="location.href='SysUser/register.jsp'">快速注册</div>
		  	</div>
			<form id="form1">
				
				<div class="zhanghaomimashuruqu">
					<div class="hang">
						<div class="shuruming">账号：</div>
						<div class="shuruzhi"><input name="loginName" id="loginname" type="text"></div>
					</div>
					<div class="hang">
						<div class="shuruming">密码：</div>
						<div class="shuruzhi"><input name="passWord" id="passWord" type="password"></div>
					</div>
					<div class="hang">
						<div class="shuruming">验证码：</div>
						<div class="yanzhengmashuru"><input id="rand" name="rand" type="text"/></div>
						<div class="yanzhengma"><img id="randImage" src="public/jsp/image.jsp" onclick="loadimage()"></div>
					</div>
				</div>
				
				<input type="button" value="登录" class="dengluOrZhuce" onclick="tijiao();">
				<a href="SysUser/forgetPassWord/1inputEmail.jsp" class="wangjimima">忘记密码</a>
			</form>
		  </div>
	
  		</td>
  	</tr>
  </table>
  </body>
  <script type="text/javascript">
  	//将历史输入的账号录入账号文本框
  	$("#loginname").val(localStorage.getItem("loginname"));
  </script>
</html>
