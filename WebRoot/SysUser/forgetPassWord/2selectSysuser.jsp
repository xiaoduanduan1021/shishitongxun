<%@page import="com.clint.sysuser.model.SysUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择账户</title>
    
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
	<link rel="stylesheet" type="text/css" href="SysUser/forgetPassWord/css/2updatePassWord.css">
	<script type="text/javascript" src="public/layer/layer.js"></script>
  </head>
  
  <body>
		<%List<SysUser> l = (List<SysUser>) request.getAttribute("l");
			
		%>
		
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
				</style>
  		  		<div class="liuchengtishi" id="liucheng1">1.录入邮箱</div>
		  		<div class="liuchengtishi" id="liucheng2">2.选择账户</div>
		  		<div class="liuchengtishi" id="liucheng3">3.录入验证码新密码</div>
		  		<div class="liuchengtishi" id="liucheng4">4.改密完成</div>
		  		<!-- 流程模块 -->
		  		
		  		
		  		
				  <div class="denglumokuai">
					<form id="form1" action="SysUser/toEmailRand.action" method="post">
						<div class="zhanghaomimashuruqu">
							<%
							if(l==null || l.size()==0){
								%>该邮箱没有账户<%
							}else{
								for(SysUser sysuser : l){
									%>
										<div class="hang">
											<label>
												<input class="xuanzeZhanghuRadio" name="id" type="radio" value="<%=sysuser.getId()%>" />
												<div class="shuruming"><%=sysuser.getLoginName() %></div>
												
											</label>
										</div>
									<%
								}
							}
							%>
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
			var value = "";
			var radio = document.getElementsByName("id");
			for (var i = 0; i < radio.length; i++) {
				if (radio[i].checked == true) {
					value = radio[i].value;
					break;
				}
			}
			if (value == "") {
				alert("请选择要改密的账户！");
				return;
			}
			
			
			var options={
					url:"SysUser/toEmailRand.action",
					type:"POST",
					dataType:"text",
					success:function(msg){
						if(msg=="ok"){
							//发送邮箱验证码完成提示“验证码已经发送到邮箱，请点击确认进入下一步”
							layer.open({
									  content: '验证码已发送到邮箱<br>请进入邮箱获取验证码<br>点击下一步获取验证码'
									  ,btn: ['下一步']
									  ,yes: function(index, layero){
											location.href="SysUser/forgetPassWord/3inputNewPassWord.jsp";
									  }
									  ,cancel: function(){ 
											location.href="SysUser/forgetPassWord/3inputNewPassWord.jsp";
									  }
								});

						}else{
							//刷新验证码
							loadimage();
						}
						if(msg=="randError"){
							layer.alert("验证码不正确");
						}

						if(msg=="sysUserNull"){
							layer.alert("请选择账户");
						}
						//邮箱发送失败，请联系管理员
						if(msg=="emailInvalid"){
							layer.alert("email发送失败请检查邮箱是否可用，或者联系管理员申诉，修改邮箱。");
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
