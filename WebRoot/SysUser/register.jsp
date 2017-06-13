<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
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
	<script type="text/javascript" src="public/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="SysUser/css/register.css">


	<script type="text/javascript">
		function loadimage(){
			document.getElementById("randImage").src= "<%=path%>/public/jsp/image.jsp?"+Math.random(); 
			//清空验证码输入框
			document.getElementById("rand").value="";
		}
		//时时检验，当输入内容后*号消失等等
		function shishiJianyan(){
			//判断用户名是否输入
			if($("#loginName").val().length==0){
				$("#zhanghaobishutishi").show();
			}else{
				$("#zhanghaobishutishi").hide();
			}
			if($("#passWord").val().length==0){
				$("#mimabishutishi").show();
			}else{
				$("#mimabishutishi").hide();
			}
			if($("#passWord2").val().length==0){
				$("#chongfubishutishi").show();
			}else{
				$("#chongfubishutishi").hide();
			}
			if($("#email").val().length==0){
				$("#youxiangbishutishi").show();
			}else{
				$("#youxiangbishutishi").hide();
			}
			
		}
		
		//跳往登录
		function denglu(){
			location.href='SysUser/login.jsp';
		}
		
		function tijiao(){
			
			var options={
					url:"SysUser/sysUserRegister.action",
					type:"POST",
					dataType:"text",
					success:function(msg){
						
						console.log(msg);
						
						if(msg=="ok"){
								layer.open({
									  content: '注册完成！已经发送激活邮件<br>请进入邮箱激活'
									  ,btn: ['确认']
									  ,yes: function(index, layero){
										denglu();
									  }
									  ,cancel: function(){ 
										denglu();
									  }
								});
						}else{
							//刷新验证码
							loadimage();
							layer.alert('注册失败请重试');
						}
						if(msg=="randError"){
							layer.alert('验证码不正确');
						}
						if(msg=="loginNameNull"){
							layer.alert("用户名不能为空");
						}
						if(msg=="passWordNull"){
							layer.alert("密码不能为空");
						}
						if(msg=="loginNameOverLength"){
							layer.alert("用户名不能超过20个字符");
						}
						if(msg=="passWordOverLength"){
							layer.alert("密码不能超过20个字符");
						}
						if(msg=="telePhoneOverLength"){
							layer.alert("手机号不能超过20个字符");
						}
						if(msg=="emailOverLength"){
							layer.alert("email不能超过50个字符");
						}
						if(msg=="companyOverLength"){
							layer.alert("公司名不能超过50个字符");
						}
						if(msg=="passWordDifferent"){
							layer.alert("两次密码不相同");
						}
						if(msg=="loginNameAlreadyExists"){
							layer.alert("用户名已经存在");
						}
						if(msg=="emailNo"){
							layer.alert("email格式不正确");
						}
						if(msg=="emailInvalid"){
							layer.alert("email无效");
						}
					},
					error:function(){
						alert("注册失败");
						//刷新验证码
						loadimage();
					}
			}
			$('#form1').ajaxSubmit(options);
		}
		
		
		$(document).ready(function(){
			$('input').bind('input propertychange', function() {
				shishiJianyan();
			});
		});
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
		  		<div class="dengluanniu" onclick="location.href='SysUser/login.jsp'">快速登录</div>
		  		<div class="zhuceanniu" onclick="location.href='SysUser/register.jsp'">快速注册</div>
		  	</div>
			<form id="form1">
				
				<div class="zhanghaomimashuruqu">
					<div class="hang">
						<div class="shuruming">账号：</div>
						<div class="shuruzhi"><input id="loginName" name="loginName" type="text"></div>
					</div>
					<div class="bishutishi" id="zhanghaobishutishi">*</div>
					<div class="wenzitishi" id="zhanghaotishi">账号长度20个字符以下</div>
					<div class="hang">
						<div class="shuruming">密码：</div>
						<div class="shuruzhi"><input name="passWord" id="passWord" type="password"></div>
					</div>
					<div class="bishutishi" id="mimabishutishi">*</div>
					<div class="wenzitishi" id="mimatishi">密码长度20个字符以下</div>
					<div class="hang">
						<div class="shuruming">重复密码：</div>
						<div class="shuruzhi"><input name="passWord2" id="passWord2" type="password"></div>
					</div>
					<div class="bishutishi" id="chongfubishutishi">*</div>
					<div class="wenzitishi" id="chongfutishi">密码长度20个字符以下</div>
					<div class="hang">
						<div class="shuruming">邮箱：</div>
						<div class="shuruzhi"><input name="email" id="email" type="text"></div>
					</div>
					<div class="bishutishi" id="youxiangbishutishi">*</div>
					<div class="wenzitishi" id="youxiangtishi">邮箱长度小于50，一个邮箱可以注册多个账号</div>
					
					<div class="hang">
						<div class="shuruming">手机：</div>
						<div class="shuruzhi"><input name="telePhone" type="text"></div>
					</div>
					<div class="wenzitishi" id="shoujitishi">手机长度20个字符以下</div>
					<div class="hang">
						<div class="shuruming">公司：</div>
						<div class="shuruzhi"><input name="company" type="text"></div>
					</div>
					<div class="wenzitishi" id="gongsitishi">公司长度50个字符以下</div>



					<div class="hang">
						<div class="shuruming">验证码：</div>
						<div class="yanzhengmashuru"><input id="rand" name="rand" type="text"/></div>
						<div class="yanzhengma"><img id="randImage" src="public/jsp/image.jsp" onclick="loadimage()"></div>
					</div>
				</div>
				
				<input type="button" value="注册" class="dengluOrZhuce" onclick="tijiao();">
			</form>
		  </div>
	
  		</td>
  	</tr>
  </table>
	</form>
  </body>
</html>
